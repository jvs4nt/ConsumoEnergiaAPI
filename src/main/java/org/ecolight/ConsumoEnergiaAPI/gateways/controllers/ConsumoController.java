package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.usecases.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<EntityModel<Consumo>> criarConsumo(@RequestBody Consumo consumo) {
        Consumo novoConsumo = consumoService.salvarConsumo(consumo);
        EntityModel<Consumo> resource = EntityModel.of(novoConsumo);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                .buscarConsumoPorId(novoConsumo.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                .listarConsumos()).withRel("all-consumos"));
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ConsumoController.class).buscarConsumoPorId(novoConsumo.getId())).toUri())
                .body(resource);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Consumo>>> listarConsumos() {
        List<EntityModel<Consumo>> consumos = consumoService.listarTodosConsumos().stream()
                .map(consumo -> {
                    EntityModel<Consumo> resource = EntityModel.of(consumo);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(consumo.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(consumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Consumo>> buscarConsumoPorId(@PathVariable Integer id) {
        return consumoService.buscarPorId(id)
                .map(consumo -> {
                    EntityModel<Consumo> resource = EntityModel.of(consumo);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(id)).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .listarConsumos()).withRel("all-consumos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Consumo>> atualizarConsumo(@PathVariable Integer id, @RequestBody Consumo consumo) {
        return consumoService.atualizarConsumo(id, consumo)
                .map(consumoAtualizado -> {
                    EntityModel<Consumo> resource = EntityModel.of(consumoAtualizado);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(consumoAtualizado.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .listarConsumos()).withRel("all-consumos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConsumo(@PathVariable Integer id) {
        if (consumoService.excluirConsumo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
