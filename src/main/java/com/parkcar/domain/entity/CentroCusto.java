package com.parkcar.domain.entity;

import com.parkcar.domain.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "centro_custo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CentroCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veiculo", nullable = false)
    private TipoVeiculo tipoVeiculo;

    @Column(name = "valor_hora", nullable = false)
    private BigDecimal valorHora;

    @Column(name = "tolerancia_minutos", nullable = false)
    private Integer toleranciaMinutos;
}
