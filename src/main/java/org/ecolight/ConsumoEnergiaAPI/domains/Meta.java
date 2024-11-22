package org.ecolight.ConsumoEnergiaAPI.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "tb_usuario_email", nullable = false)
    private String usuarioEmail;
}
