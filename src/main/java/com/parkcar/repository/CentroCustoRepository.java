package com.parkcar.repository;

import com.parkcar.domain.entity.CentroCusto;
import com.parkcar.domain.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    Optional<CentroCusto> findByTipoVeiculo(TipoVeiculo tipoVeiculo);
}
