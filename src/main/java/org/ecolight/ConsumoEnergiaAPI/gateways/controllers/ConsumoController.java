package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.usecases.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @PostMapping
    public ResponseEntity<Consumo> criarConsumo(@RequestBody Consumo consumo) {
        Consumo novoConsumo = consumoService.salvarConsumo(consumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoConsumo);
    }

    @GetMapping
    public ResponseEntity<List<Consumo>> listarConsumos() {
        List<Consumo> consumos = consumoService.listarTodosConsumos();
        return ResponseEntity.ok(consumos);
    }
}
