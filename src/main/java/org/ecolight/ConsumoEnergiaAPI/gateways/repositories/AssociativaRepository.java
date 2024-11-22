package org.ecolight.ConsumoEnergiaAPI.gateways.repositories;

import org.ecolight.ConsumoEnergiaAPI.domains.Associativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociativaRepository extends JpaRepository<Associativa, Integer> {
}
