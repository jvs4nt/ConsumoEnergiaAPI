package org.ecolight.ConsumoEnergiaAPI.gateways.repositories;

import org.ecolight.ConsumoEnergiaAPI.domains.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Integer> {
}
