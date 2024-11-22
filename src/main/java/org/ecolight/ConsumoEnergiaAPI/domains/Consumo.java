package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "TB_CONSUMO")
@Data
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONSUMO")
    private Integer id;

    @Column(name = "DT_USO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataUso;

    @Column(name = "TEMPO_USO", nullable = false)
    private Double tempoUso;

    @Column(name = "TOTAL_CONSUMO", nullable = false)
    private Double totalConsumo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_ASSOC_ID_USUARIO", referencedColumnName = "TB_USUARIO_ID_USUARIO", nullable = false)
    private Associativa associativaUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_ASSOC_DISPOSITIVO", referencedColumnName = "TB_DISPOSITIVO_ID_DISPOS", nullable = false)
    private Associativa associativaDispositivo;
}
