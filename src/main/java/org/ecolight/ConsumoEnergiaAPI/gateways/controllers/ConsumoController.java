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
}
