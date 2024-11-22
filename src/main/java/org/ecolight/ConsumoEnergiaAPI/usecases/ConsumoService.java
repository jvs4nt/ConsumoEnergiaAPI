package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Transactional
    public Consumo salvarConsumo(Consumo consumo) {
        return consumoRepository.save(consumo);
    }

    @Transactional
    public List<Consumo> listarTodosConsumos() {
        return consumoRepository.findAll();
    }

    @Transactional
    public Optional<Consumo> buscarPorId(Integer id) {
        return consumoRepository.findById(id);
    }

    @Transactional
    public Optional<Consumo> atualizarConsumo(Integer id, Consumo consumoAtualizado) {
        return consumoRepository.findById(id).map(consumoExistente -> {
            consumoExistente.setDataUso(consumoAtualizado.getDataUso());
            consumoExistente.setTempoUso(consumoAtualizado.getTempoUso());
            consumoExistente.setTotalConsumo(consumoAtualizado.getTotalConsumo());
            consumoExistente.setAssociativaUsuario(consumoAtualizado.getAssociativaUsuario());
            consumoExistente.setAssociativaDispositivo(consumoAtualizado.getAssociativaDispositivo());
            return consumoRepository.save(consumoExistente);
        });
    }

    @Transactional
    public boolean excluirConsumo(Integer id) {
        if (consumoRepository.existsById(id)) {
            consumoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
