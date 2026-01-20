package com.parkcar.repository;

import com.parkcar.domain.entity.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {

    // Verifica se o veículo já está estacionado
    Optional<Estacionamento> findByVeiculo_Placa(String placa);

    // Conta quantos veículos estão atualmente na garagem
    long countByGaragem_Id(Long garagemId);
}