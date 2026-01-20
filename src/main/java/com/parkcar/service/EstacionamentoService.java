package com.parkcar.service;

import com.parkcar.domain.entity.*;
import com.parkcar.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Transactional
public class EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;
    private final VeiculoRepository veiculoRepository;
    private final GaragemRepository garagemRepository;
    private final CentroCustoRepository centroCustoRepository;
    private final HistoricoEstacionamentoRepository historicoRepository;

    public EstacionamentoService(
            EstacionamentoRepository estacionamentoRepository,
            VeiculoRepository veiculoRepository,
            GaragemRepository garagemRepository,
            CentroCustoRepository centroCustoRepository,
            HistoricoEstacionamentoRepository historicoRepository) {

        this.estacionamentoRepository = estacionamentoRepository;
        this.veiculoRepository = veiculoRepository;
        this.garagemRepository = garagemRepository;
        this.centroCustoRepository = centroCustoRepository;
        this.historicoRepository = historicoRepository;
    }

    public Estacionamento registrarEntrada(String placa, Long garagemId) {

        estacionamentoRepository
                .findByVeiculo_Placa(placa)
                .ifPresent(e -> {
                    throw new IllegalStateException("Veículo já está estacionado");
                });

        Veiculo veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));

        Garagem garagem = garagemRepository.findById(garagemId)
                .orElseThrow(() -> new IllegalArgumentException("Garagem não encontrada"));

        long ocupadas = estacionamentoRepository.countByGaragem_Id(garagemId);
        if (ocupadas >= garagem.getTotalVagas()) {
            throw new IllegalStateException("Garagem lotada");
        }

        Estacionamento estacionamento = Estacionamento.builder()
                .veiculo(veiculo)
                .garagem(garagem)
                .build();

        estacionamentoRepository.save(estacionamento);

        return estacionamento;
    }


    public HistoricoEstacionamento registrarSaida(String placa) {

        Estacionamento estacionamento = estacionamentoRepository
                .findByVeiculo_Placa(placa)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não está estacionado"));

        LocalDateTime saida = LocalDateTime.now();

        CentroCusto centroCusto = centroCustoRepository
                .findByTipoVeiculo(estacionamento.getVeiculo().getTipo())
                .orElseThrow(() -> new IllegalStateException("Centro de custo não configurado"));

        BigDecimal valor = calcularValor(
                estacionamento.getEntrada(),
                saida,
                centroCusto
        );

        HistoricoEstacionamento historico = HistoricoEstacionamento.builder()
                .veiculo(estacionamento.getVeiculo())
                .garagem(estacionamento.getGaragem())
                .entrada(estacionamento.getEntrada())
                .saida(saida)
                .valorCobrado(valor)
                .build();

        estacionamentoRepository.delete(estacionamento);

        return historicoRepository.save(historico);
    }

    private BigDecimal calcularValor(
            LocalDateTime entrada,
            LocalDateTime saida,
            CentroCusto centroCusto) {

        long minutos = Duration.between(entrada, saida).toMinutes();

        if (minutos <= centroCusto.getToleranciaMinutos()) {
            return BigDecimal.ZERO;
        }

        long minutosCobrados = minutos - centroCusto.getToleranciaMinutos();
        long horas = (long) Math.ceil(minutosCobrados / 60.0);

        return centroCusto.getValorHora()
                .multiply(BigDecimal.valueOf(horas));
    }
}
