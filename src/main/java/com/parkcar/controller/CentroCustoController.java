package com.parkcar.controller;

import com.parkcar.domain.entity.CentroCusto;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.repository.CentroCustoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centro-custo")
public class CentroCustoController {

    private final CentroCustoRepository repository;

    public CentroCustoController(CentroCustoRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity criar(@RequestBody CentroCusto centroCusto) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.save(centroCusto))
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping
    public ResponseEntity listar() {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.findAll())
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
