package com.parkcar.dto;

import com.parkcar.domain.enums.TipoVeiculo;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private String error;
    private int statusCode;
    private Object data;
}