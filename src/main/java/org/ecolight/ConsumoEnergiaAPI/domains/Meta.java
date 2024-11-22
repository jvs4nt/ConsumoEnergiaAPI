package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "TB_META")
@Data
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_META")
    private Integer id;

    @Column(name = "VLR_META", nullable = false)
    private Double valorMeta;

    @Column(name = "DT_CADASTRO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TB_USUARIO_ID_USUARIO", nullable = false)
    private Usuario usuario;
}
