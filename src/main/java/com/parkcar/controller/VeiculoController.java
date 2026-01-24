package com.parkcar.controller;

import com.parkcar.domain.entity.Veiculo;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.dto.VeiculoRequestDTO;
import com.parkcar.repository.VeiculoRepository;
import com.parkcar.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoRepository repository;
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoRepository repository, VeiculoService veiculoService) {
        this.repository = repository;
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody VeiculoRequestDTO veiculoDto) {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(veiculoService.Criar(veiculoDto))
                .statusCode(HttpStatus.CREATED.value())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {

                veiculoService.Deletar(id);

               return ResponseEntity.ok(
                        ResponseDTO.builder()
                                .statusCode(HttpStatus.OK.value())
                                .build());
    }

    @GetMapping
    public ResponseEntity listar() {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(veiculoService.Listar())
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/{placa}")
    public ResponseEntity buscarPorPlaca(@PathVariable String placa) {

        return veiculoService.BuscarPorPlaca(placa)
                .map(veiculo -> ResponseEntity.ok(
                        ResponseDTO.builder()
                                .statusCode(HttpStatus.OK.value())
                                .data(veiculo)
                                .build()
                ))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseDTO.builder()
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .error("Veículo não encontrado")
                                .build()
                        ));
    }
}
