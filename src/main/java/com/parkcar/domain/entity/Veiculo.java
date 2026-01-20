package com.parkcar.domain.entity;

import com.parkcar.domain.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculo")
@Getter
@Setter
@NoArgsConstructor
@ AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String placa;

    @Column(name = "nome_proprietario", nullable = false)
    private String nomeProprietario;

    @Column(name = "tipo_veiculo_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipo;
}