package com.parkcar.dto;

import com.parkcar.domain.enums.TipoVeiculo;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class VeiculoRequestDTO {
    private String placa;
    private String nomeProprietario;
    private TipoVeiculo tipo;
}