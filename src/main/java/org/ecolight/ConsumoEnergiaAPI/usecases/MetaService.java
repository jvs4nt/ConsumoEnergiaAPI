package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Meta;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    @Transactional
    public Meta salvarMeta(Meta meta) {
        return metaRepository.save(meta);
    }

    @Transactional
    public List<Meta> listarTodasMetas() {
        return metaRepository.findAll();
    }

    @Transactional
    public Optional<Meta> buscarPorId(Integer id) {
        return metaRepository.findById(id);
    }

    @Transactional
    public Optional<Meta> atualizarMeta(Integer id, Meta metaAtualizada) {
        return metaRepository.findById(id).map(metaExistente -> {
            metaExistente.setValorMeta(metaAtualizada.getValorMeta());
            metaExistente.setDataCadastro(metaAtualizada.getDataCadastro());
            metaExistente.setUsuarioEmail(metaAtualizada.getUsuarioEmail());
            return metaRepository.save(metaExistente);
        });
    }

    @Transactional
    public boolean excluirMeta(Integer id) {
        if (metaRepository.existsById(id)) {
            metaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
