package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "TB_USUARIO")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "NM_USUARIO", nullable = false, length = 150)
    private String nome;

    @Column(name = "EMAIL", nullable = false, length = 300)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 16)
    private String senha;

    @Column(name = "TELEFONE", length = 11)
    private Long telefone;

    @Column(name = "DT_CADASTRO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "GENERO", length = 1)
    private String genero;
}
