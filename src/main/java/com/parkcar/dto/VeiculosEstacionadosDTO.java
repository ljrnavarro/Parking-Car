package com.parkcar.dto;

import com.parkcar.domain.enums.TipoVeiculo;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class VeiculosEstacionadosDTO {
    private String placa;
    private String nomeProprietario;
    private long garagem;
    private LocalDateTime entrada;

    public VeiculosEstacionadosDTO(String placa, String nomeProprietario, long garagem, LocalDateTime entrada) {
        this.placa = placa;
        this.nomeProprietario = nomeProprietario;
        this.garagem = garagem;
        this.entrada = entrada;
    }
}