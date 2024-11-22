package org.ecolight.ConsumoEnergiaAPI.gateways.controllers;

import org.ecolight.ConsumoEnergiaAPI.domains.Dispositivo;
import org.ecolight.ConsumoEnergiaAPI.usecases.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Dispositivo> criarDispositivo(@RequestBody Dispositivo dispositivo) {
        Dispositivo novoDispositivo = dispositivoService.salvarDispositivo(dispositivo);
        return ResponseEntity.status(201).body(novoDispositivo);
    }

    @GetMapping
    public ResponseEntity<List<Dispositivo>> listarDispositivos() {
        return ResponseEntity.ok(dispositivoService.listarTodosDispositivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> buscarDispositivoPorId(@PathVariable Integer id) {
        Optional<Dispositivo> dispositivo = dispositivoService.buscarPorId(id);
        return dispositivo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> atualizarDispositivo(@PathVariable Integer id, @RequestBody Dispositivo dispositivo) {
        Optional<Dispositivo> dispositivoAtualizado = dispositivoService.atualizarDispositivo(id, dispositivo);
        return dispositivoAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDispositivo(@PathVariable Integer id) {
        if (dispositivoService.excluirDispositivo(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
