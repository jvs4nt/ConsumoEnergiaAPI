package org.ecolight.ConsumoEnergiaAPI.usecases;

import jakarta.transaction.Transactional;
import org.ecolight.ConsumoEnergiaAPI.domains.Consumo;
import org.ecolight.ConsumoEnergiaAPI.gateways.repositories.ConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

