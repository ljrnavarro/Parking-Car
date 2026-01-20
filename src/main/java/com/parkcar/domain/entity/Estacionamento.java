package com.parkcar.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "estacionamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "garagem_id")
    private Garagem garagem;

    @Column(name = "entrada", nullable = false)
    @CreationTimestamp
    private LocalDateTime entrada;
}