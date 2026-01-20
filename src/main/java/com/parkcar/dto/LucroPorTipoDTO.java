package com.parkcar.dto;

import com.parkcar.domain.enums.TipoVeiculo;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class LucroPorTipoDTO {
    private TipoVeiculo tipo;
    private BigDecimal total;

    public LucroPorTipoDTO(TipoVeiculo tipo, BigDecimal total) {
        this.tipo = tipo;
        this.total = total;
    }

}