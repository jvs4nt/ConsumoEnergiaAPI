package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecolight.ConsumoEnergiaAPI.domains.Dispositivo;
import org.ecolight.ConsumoEnergiaAPI.usecases.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dispositivos")
@Tag(name = "Dispositivos", description = "Gerenciamento de dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    @Operation(summary = "Criar um novo dispositivo", description = "Adiciona um novo dispositivo ao sistema")
    public ResponseEntity<EntityModel<Dispositivo>> criarDispositivo(@RequestBody Dispositivo dispositivo) {
        Dispositivo novoDispositivo = dispositivoService.salvarDispositivo(dispositivo);
        EntityModel<Dispositivo> resource = EntityModel.of(novoDispositivo);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                .buscarDispositivoPorId(novoDispositivo.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                .listarDispositivos()).withRel("all-dispositivos"));
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                .buscarDispositivoPorId(novoDispositivo.getId())).toUri()).body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar todos os dispositivos", description = "Retorna uma lista de todos os dispositivos registrados")
    public ResponseEntity<List<EntityModel<Dispositivo>>> listarDispositivos() {
        List<EntityModel<Dispositivo>> dispositivos = dispositivoService.listarTodosDispositivos().stream()
                .map(dispositivo -> {
                    EntityModel<Dispositivo> resource = EntityModel.of(dispositivo);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                            .buscarDispositivoPorId(dispositivo.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dispositivos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar dispositivo por ID", description = "Retorna um dispositivo específico pelo ID fornecido")
    public ResponseEntity<EntityModel<Dispositivo>> buscarDispositivoPorId(@PathVariable Integer id) {
        return dispositivoService.buscarPorId(id)
                .map(dispositivo -> {
                    EntityModel<Dispositivo> resource = EntityModel.of(dispositivo);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                            .buscarDispositivoPorId(id)).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                            .listarDispositivos()).withRel("all-dispositivos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um dispositivo", description = "Atualiza os dados de um dispositivo existente")
    public ResponseEntity<EntityModel<Dispositivo>> atualizarDispositivo(@PathVariable Integer id, @RequestBody Dispositivo dispositivo) {
        return dispositivoService.atualizarDispositivo(id, dispositivo)
                .map(dispositivoAtualizado -> {
                    EntityModel<Dispositivo> resource = EntityModel.of(dispositivoAtualizado);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                            .buscarDispositivoPorId(dispositivoAtualizado.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DispositivoController.class)
                            .listarDispositivos()).withRel("all-dispositivos"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um dispositivo", description = "Remove um dispositivo existente pelo ID fornecido")
    public ResponseEntity<Void> excluirDispositivo(@PathVariable Integer id) {
        if (dispositivoService.excluirDispositivo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
