package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Associativa;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.gateways.dtos.ConsumoDTO;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.AssociativaRepository;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private AssociativaRepository associativaRepository;

    @Transactional
    public Consumo salvarConsumo(Consumo consumo) {
        if (consumo.getAssociativa() != null && consumo.getAssociativa().getId() != null) {
            Associativa associativa = associativaRepository.findById(consumo.getAssociativa().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Associativa não encontrada com ID: " + consumo.getAssociativa().getId()));
            consumo.setAssociativa(associativa);
        }
        return consumoRepository.save(consumo);
    }

    @Transactional
    public List<ConsumoDTO> listarTodosConsumos() {
        return consumoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ConsumoDTO> buscarPorId(Integer id) {
        return consumoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ConsumoDTO convertToDTO(Consumo consumo) {
        return new ConsumoDTO(
                consumo.getId(),
                consumo.getDataUso(),
                consumo.getTempoUso(),
                consumo.getTotalConsumo(),
                consumo.getAssociativa() != null ? consumo.getAssociativa().getId() : null
        );
    }

    @Transactional
    public Optional<Consumo> atualizarConsumo(Integer id, Consumo consumoAtualizado) {
        return consumoRepository.findById(id).map(consumoExistente -> {
            consumoExistente.setDataUso(consumoAtualizado.getDataUso());
            consumoExistente.setTempoUso(consumoAtualizado.getTempoUso());
            consumoExistente.setTotalConsumo(consumoAtualizado.getTotalConsumo());

            if (consumoAtualizado.getAssociativa() != null && consumoAtualizado.getAssociativa().getId() != null) {
                Associativa associativa = associativaRepository.findById(consumoAtualizado.getAssociativa().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Associativa não encontrada com ID: " + consumoAtualizado.getAssociativa().getId()));
                consumoExistente.setAssociativa(associativa);
            }

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
