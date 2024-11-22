package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import org.ecolight.ConsumoEnergiaAPI.domains.Meta;
import org.ecolight.ConsumoEnergiaAPI.usecases.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @PostMapping
    public ResponseEntity<Meta> criarMeta(@RequestBody Meta meta) {
        Meta novaMeta = metaService.salvarMeta(meta);
        return ResponseEntity.status(201).body(novaMeta);
    }

    @GetMapping
    public ResponseEntity<List<Meta>> listarMetas() {
        return ResponseEntity.ok(metaService.listarTodasMetas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meta> buscarMetaPorId(@PathVariable Integer id) {
        Optional<Meta> meta = metaService.buscarPorId(id);
        return meta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meta> atualizarMeta(@PathVariable Integer id, @RequestBody Meta meta) {
        Optional<Meta> metaAtualizada = metaService.atualizarMeta(id, meta);
        return metaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMeta(@PathVariable Integer id) {
        if (metaService.excluirMeta(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
