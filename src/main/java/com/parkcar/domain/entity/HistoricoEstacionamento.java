package com.parkcar.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_estacionamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "garagem_id")
    private Garagem garagem;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime entrada;

    @Column(name = "data_saida")
    private LocalDateTime saida;

    @Column(name = "valor_total")
    private BigDecimal valorCobrado;
}