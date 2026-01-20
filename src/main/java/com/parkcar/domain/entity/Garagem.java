package com.parkcar.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "garagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "total_vagas", nullable = false)
    private Integer totalVagas;
}