package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_ASSOC_USUARIO_DISP")
@Data
public class Associativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASSOCIATIVA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_USUARIO_ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_DISPOSITIVO_ID_DISPOS", nullable = false)
    private Dispositivo dispositivo;
}
