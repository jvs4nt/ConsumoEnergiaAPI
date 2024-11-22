package org.ecolight.ConsumoEnergiaAPI.gateways.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumoDTO {
    private Integer id;
    private Date dataUso;
    private Double tempoUso;
    private Double totalConsumo;
    private Integer associativaId;

    public ConsumoDTO(Integer id, Date dataUso, Double tempoUso, Double totalConsumo, Integer associativaId) {
        this.id = id;
        this.dataUso = dataUso;
        this.tempoUso = tempoUso;
        this.totalConsumo = totalConsumo;
        this.associativaId = associativaId;
    }
}
