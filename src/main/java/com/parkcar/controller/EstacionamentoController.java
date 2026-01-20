package com.parkcar.controller;

import com.parkcar.domain.entity.*;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.dto.VeiculosEstacionadosDTO;
import com.parkcar.repository.EstacionamentoRepository;
import com.parkcar.repository.GaragemRepository;
import com.parkcar.repository.HistoricoEstacionamentoRepository;
import com.parkcar.service.EstacionamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService service;
    private final HistoricoEstacionamentoRepository repository;

    public EstacionamentoController(EstacionamentoService service, HistoricoEstacionamentoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/")
    public ResponseEntity veiculosEstacionados()
    {
       return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.veiculosEstacionados())
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    // 🚗 Entrada
    @PostMapping("/entrada")
    public ResponseEntity entrada(
            @RequestParam String placa,
            @RequestParam Long garagemId) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(service.registrarEntrada(placa, garagemId))
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    // ⏱️ Saída
    @PostMapping("/saida")
    public ResponseEntity saida(
            @RequestParam String placa) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(service.registrarSaida(placa))
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
