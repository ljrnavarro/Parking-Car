package com.parkcar.controller;

import com.parkcar.domain.enums.TipoVeiculo;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.repository.HistoricoEstacionamentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final HistoricoEstacionamentoRepository repository;

    public RelatorioController(HistoricoEstacionamentoRepository repository) {
        this.repository = repository;
    }

    // 🔹 placeholder (vamos implementar já já)
    @GetMapping("/lucro-dia")
    public ResponseEntity lucroPorDia(
            @RequestParam LocalDate data) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.lucroTotalPorDia(data))
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/lucro-por-tipo")
    public ResponseEntity lucroPorTipo(
            @RequestParam String tipoVeiculo,
            @RequestParam LocalDate data) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.faturamentoPorTipo(TipoVeiculo.valueOf(tipoVeiculo),data.atStartOfDay(), data.atStartOfDay()))
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
