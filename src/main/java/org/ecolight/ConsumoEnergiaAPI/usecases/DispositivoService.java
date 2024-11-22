package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Dispositivo;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Transactional
    public Dispositivo salvarDispositivo(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    @Transactional
    public List<Dispositivo> listarTodosDispositivos() {
        return dispositivoRepository.findAll();
    }

    @Transactional
    public Optional<Dispositivo> buscarPorId(Integer id) {
        return dispositivoRepository.findById(id);
    }

    @Transactional
    public Optional<Dispositivo> atualizarDispositivo(Integer id, Dispositivo dispositivoAtualizado) {
        return dispositivoRepository.findById(id).map(dispositivoExistente -> {
            dispositivoExistente.setNome(dispositivoAtualizado.getNome());
            dispositivoExistente.setPotencia(dispositivoAtualizado.getPotencia());
            return dispositivoRepository.save(dispositivoExistente);
        });
    }

    @Transactional
    public boolean excluirDispositivo(Integer id) {
        if (dispositivoRepository.existsById(id)) {
            dispositivoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
