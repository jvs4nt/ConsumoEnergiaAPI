package org.ecolight.ConsumoEnergiaAPI.gateways.repositories;

import org.ecolight.ConsumoEnergiaAPI.domains.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {
}
