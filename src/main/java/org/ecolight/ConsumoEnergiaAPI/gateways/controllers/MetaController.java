package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecolight.ConsumoEnergiaAPI.domains.Meta;
import org.ecolight.ConsumoEnergiaAPI.usecases.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/metas")
@Tag(name = "Metas", description = "Gerenciamento de metas do sistema")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @PostMapping
    @Operation(summary = "Criar uma nova meta", description = "Adiciona uma nova meta ao sistema")
    public ResponseEntity<EntityModel<Meta>> criarMeta(@RequestBody Meta meta) {
        Meta novaMeta = metaService.salvarMeta(meta);
        EntityModel<Meta> resource = EntityModel.of(novaMeta);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                .buscarMetaPorId(novaMeta.getId())).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                .listarMetas()).withRel("all-metas"));
        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                .buscarMetaPorId(novaMeta.getId())).toUri()).body(resource);
    }

    @GetMapping
    @Operation(summary = "Listar todas as metas", description = "Retorna uma lista de todas as metas registradas")
    public ResponseEntity<List<EntityModel<Meta>>> listarMetas() {
        List<EntityModel<Meta>> metas = metaService.listarTodasMetas().stream()
                .map(meta -> {
                    EntityModel<Meta> resource = EntityModel.of(meta);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .buscarMetaPorId(meta.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(metas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar meta por ID", description = "Retorna uma meta específica pelo ID fornecido")
    public ResponseEntity<EntityModel<Meta>> buscarMetaPorId(@PathVariable Integer id) {
        return metaService.buscarPorId(id)
                .map(meta -> {
                    EntityModel<Meta> resource = EntityModel.of(meta);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .buscarMetaPorId(id)).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .listarMetas()).withRel("all-metas"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar meta por Email", description = "Retorna a meta de um usuário específico pelo email fornecido")
    public ResponseEntity<EntityModel<Meta>> buscarMetaPorEmail(@PathVariable String email) {
        return metaService.buscarPorEmail(email)
                .map(meta -> {
                    EntityModel<Meta> resource = EntityModel.of(meta);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .buscarMetaPorId(meta.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .listarMetas()).withRel("all-metas"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma meta", description = "Atualiza os dados de uma meta existente")
    public ResponseEntity<EntityModel<Meta>> atualizarMeta(@PathVariable Integer id, @RequestBody Meta meta) {
        return metaService.atualizarMeta(id, meta)
                .map(metaAtualizada -> {
                    EntityModel<Meta> resource = EntityModel.of(metaAtualizada);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .buscarMetaPorId(metaAtualizada.getId())).withSelfRel());
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MetaController.class)
                            .listarMetas()).withRel("all-metas"));
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma meta", description = "Remove uma meta existente pelo ID fornecido")
    public ResponseEntity<Void> excluirMeta(@PathVariable Integer id) {
        if (metaService.excluirMeta(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
