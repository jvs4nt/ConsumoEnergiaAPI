package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "tb_meta")
@Data
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Integer id;

    @Column(name = "vlr_meta", nullable = false)
    private Double valorMeta;

    @Column(name = "dt_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_usuario_id_usuario", nullable = false)
    private Usuario usuario;
}
