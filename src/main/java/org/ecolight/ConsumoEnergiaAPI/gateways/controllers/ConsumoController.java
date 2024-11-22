package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.gateways.dtos.ConsumoDTO;
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
@Tag(name = "Consumos", description = "Gerenciamento de consumos de energia")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    @Operation(summary = "Criar um novo consumo", description = "Adiciona um novo consumo ao sistema")
    public ResponseEntity<EntityModel<ConsumoDTO>> criarConsumo(@RequestBody Consumo consumo) {
        Consumo novoConsumo = consumoService.salvarConsumo(consumo);
        ConsumoDTO consumoDTO = consumoService.convertToDTO(novoConsumo);
        EntityModel<ConsumoDTO> resource = EntityModel.of(consumoDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                .buscarConsumoPorId(consumoDTO.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                .listarConsumos()).withRel("all-consumos"));
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ConsumoController.class).buscarConsumoPorId(consumoDTO.getId())).toUri())
                .body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar todos os consumos", description = "Retorna uma lista com todos os consumos registrados")
    public ResponseEntity<List<EntityModel<ConsumoDTO>>> listarConsumos() {
        List<EntityModel<ConsumoDTO>> consumos = consumoService.listarTodosConsumos().stream()
                .map(consumoDTO -> {
                    EntityModel<ConsumoDTO> resource = EntityModel.of(consumoDTO);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(consumoDTO.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(consumos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consumo por ID", description = "Retorna um consumo específico pelo ID fornecido")
    public ResponseEntity<EntityModel<ConsumoDTO>> buscarConsumoPorId(@PathVariable Integer id) {
        return consumoService.buscarPorId(id)
                .map(consumoDTO -> {
                    EntityModel<ConsumoDTO> resource = EntityModel.of(consumoDTO);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(id)).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .listarConsumos()).withRel("all-consumos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um consumo", description = "Atualiza os dados de um consumo existente pelo ID")
    public ResponseEntity<EntityModel<ConsumoDTO>> atualizarConsumo(@PathVariable Integer id, @RequestBody Consumo consumo) {
        return consumoService.atualizarConsumo(id, consumo)
                .map(consumoAtualizado -> {
                    ConsumoDTO consumoDTO = consumoService.convertToDTO(consumoAtualizado);
                    EntityModel<ConsumoDTO> resource = EntityModel.of(consumoDTO);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .buscarConsumoPorId(consumoDTO.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsumoController.class)
                            .listarConsumos()).withRel("all-consumos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um consumo", description = "Remove um consumo específico pelo ID")
    public ResponseEntity<Void> excluirConsumo(@PathVariable Integer id) {
        if (consumoService.excluirConsumo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
