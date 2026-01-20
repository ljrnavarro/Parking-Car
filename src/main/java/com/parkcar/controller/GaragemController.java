package com.parkcar.controller;

import com.parkcar.domain.entity.Garagem;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.repository.GaragemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garagens")
public class GaragemController {

    private final GaragemRepository repository;

    public GaragemController(GaragemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody Garagem garagem) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.save(garagem))
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
