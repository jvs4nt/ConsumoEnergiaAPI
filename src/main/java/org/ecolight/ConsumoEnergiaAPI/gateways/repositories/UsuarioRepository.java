package org.ecolight.ConsumoEnergiaAPI.gateways.repositories;

import org.ecolight.ConsumoEnergiaAPI.domains.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Page<Usuario> findAll(Pageable pageable);
}
