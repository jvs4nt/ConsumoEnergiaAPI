package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_dispositivo")
@Data
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Integer id;

    @Column(name = "nm_dispositivo", nullable = false, length = 250)
    private String nome;

    @Column(name = "potencia", nullable = false)
    private Double potencia;
}
