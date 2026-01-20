package com.parkcar.repository;

import com.parkcar.domain.entity.Estacionamento;
import com.parkcar.domain.entity.HistoricoEstacionamento;
import com.parkcar.domain.enums.TipoVeiculo;
import com.parkcar.dto.LucroPorTipoDTO;
import com.parkcar.dto.VeiculosEstacionadosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoricoEstacionamentoRepository
        extends JpaRepository<HistoricoEstacionamento, Long> {

    Optional<Estacionamento>
    findByVeiculo_Placa(String placa);

    // 🔍 Última movimentação do veículo (para saída)
    @Query("""
        SELECT h FROM HistoricoEstacionamento h
        WHERE h.veiculo.placa = :placa
        ORDER BY h.entrada DESC
    """)
    List<HistoricoEstacionamento> findUltimaMovimentacao(@Param("placa") String placa);

    // 💰 Faturamento total por período
    @Query("""
        SELECT COALESCE(SUM(h.valorCobrado), 0)
        FROM HistoricoEstacionamento h
        WHERE h.saida BETWEEN :inicio AND :fim
    """)
    java.math.BigDecimal faturamentoPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    // 💰 Faturamento por tipo de veículo
    @Query("""
        SELECT COALESCE(SUM(h.valorCobrado), 0)
        FROM HistoricoEstacionamento h
        WHERE h.veiculo.tipo = :tipo
          AND h.saida BETWEEN :inicio AND :fim
    """)
    java.math.BigDecimal faturamentoPorTipo(
            @Param("tipo") TipoVeiculo tipo,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    @Query("""
        SELECT COALESCE(SUM(h.valorCobrado), 0)
        FROM HistoricoEstacionamento h
        WHERE DATE(h.saida) = :data
    """)
    BigDecimal lucroTotalPorDia(@Param("data") LocalDate data);

    @Query("SELECT new com.parkcar.dto.LucroPorTipoDTO(h.veiculo.tipo, SUM(h.valorCobrado)) " +
            "FROM HistoricoEstacionamento h " +
            "WHERE FUNCTION('DATE', h.saida) = :data " +
            "GROUP BY h.veiculo.tipo")
    List<LucroPorTipoDTO> lucroPorTipoNoDia(@Param("data") LocalDate data);

    //VeiculosEstacionadosDTO(String placa, String nomeProprietario, long garagem, LocalDateTime entrada
    @Query("""
    SELECT new com.parkcar.dto.VeiculosEstacionadosDTO(
        e.veiculo.placa,
        e.veiculo.nomeProprietario,
        e.garagem.id,
        e.entrada
    )
    FROM Estacionamento e
""")
    List<VeiculosEstacionadosDTO> veiculosEstacionados();
}