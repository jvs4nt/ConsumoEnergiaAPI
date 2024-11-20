package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nm_usuario", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 300)
    private String email;

    @Column(name = "senha", nullable = false, length = 16)
    private String senha;

    @Column(name = "telefone", length = 11)
    private Long telefone;

    @Column(name = "dt_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "genero", length = 1)
    private String genero;
}
