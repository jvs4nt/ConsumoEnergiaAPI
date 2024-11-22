package org.ecolight.ConsumoEnergiaAPI.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TB_DISPOSITIVO")
@Data
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISPOSITIVO")
    private Integer id;

    @Column(name = "NM_DISPOSITIVO", nullable = false, length = 250)
    private String nome;

    @Column(name = "POTENCIA", nullable = false)
    private Double potencia;
}
