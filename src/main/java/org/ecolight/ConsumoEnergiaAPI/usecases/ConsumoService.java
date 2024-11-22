package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Associativa;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.domains.Dispositivo;
import org.ecolight.ConsumoEnergiaAPI.domains.Usuario;
import org.ecolight.ConsumoEnergiaAPI.gateways.dtos.ConsumoDTO;
import org.ecolight.ConsumoEnergiaAPI.gateways.dtos.ConsumoRequest;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.AssociativaRepository;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.ConsumoRepository;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.DispositivoRepository;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Transactional
    public Consumo salvarConsumo(ConsumoRequest consumoRequest) {
        Associativa associativa = associativaRepository.findByUsuarioIdAndDispositivoId(
                consumoRequest.getUsuarioId(),
                consumoRequest.getDispositivoId()
        ).orElseGet(() -> {
            Usuario usuario = usuarioRepository.findById(consumoRequest.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + consumoRequest.getUsuarioId()));
            Dispositivo dispositivo = dispositivoRepository.findById(consumoRequest.getDispositivoId())
                    .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado com ID: " + consumoRequest.getDispositivoId()));

            Associativa novaAssociativa = new Associativa();
            novaAssociativa.setUsuario(usuario);
            novaAssociativa.setDispositivo(dispositivo);
            return associativaRepository.save(novaAssociativa);
        });

        Consumo consumo = new Consumo();
        consumo.setDataUso(consumoRequest.getDataUso());
        consumo.setTempoUso(consumoRequest.getTempoUso());
        consumo.setTotalConsumo(consumoRequest.getTotalConsumo());
        consumo.setAssociativa(associativa);

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
