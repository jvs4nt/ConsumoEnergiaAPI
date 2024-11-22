package org.ecolight.ConsumoEnergiaAPI.gateways.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumoRequest {
    private Date dataUso;
    private Double tempoUso;
    private Double totalConsumo;
    private Integer usuarioId;
    private Integer dispositivoId;
}
