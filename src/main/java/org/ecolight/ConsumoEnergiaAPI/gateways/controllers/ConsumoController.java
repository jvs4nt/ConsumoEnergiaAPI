package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.usecases.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumos")
@Tag(name = "Consumos", description = "Gerenciamento de consumos de energia")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    @Operation(summary = "Criar um novo consumo", description = "Adiciona um novo consumo ao sistema")
    public ResponseEntity<Consumo> criarConsumo(@RequestBody Consumo consumo) {
        Consumo novoConsumo = consumoService.salvarConsumo(consumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoConsumo);
    }

    @GetMapping
    @Operation(summary = "Listar todos os consumos", description = "Retorna uma lista com todos os consumos registrados")
    public ResponseEntity<List<Consumo>> listarConsumos() {
        List<Consumo> consumos = consumoService.listarTodosConsumos();
        return ResponseEntity.ok(consumos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar consumo por ID", description = "Retorna um consumo específico pelo ID")
    public ResponseEntity<Consumo> buscarConsumoPorId(@PathVariable Integer id) {
        return consumoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um consumo", description = "Atualiza um consumo existente pelo ID")
    public ResponseEntity<Consumo> atualizarConsumo(@PathVariable Integer id, @RequestBody Consumo consumo) {
        return consumoService.atualizarConsumo(id, consumo)
                .map(ResponseEntity::ok)
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
