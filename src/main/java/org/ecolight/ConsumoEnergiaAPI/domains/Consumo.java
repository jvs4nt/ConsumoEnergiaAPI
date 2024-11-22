package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "tb_consumo")
@Data
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consumo")
    private Integer id;

    @Column(name = "dt_uso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataUso;

    @Column(name = "tempo_uso", nullable = false)
    private Double tempoUso;

    @Column(name = "total_consumo", nullable = false)
    private Double totalConsumo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_assoc_id_assoc", referencedColumnName = "id_associativa", nullable = false)
    private Associativa associativa;
}
