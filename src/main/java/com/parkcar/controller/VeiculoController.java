package com.parkcar.controller;

import com.parkcar.domain.entity.Veiculo;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.dto.VeiculoRequestDTO;
import com.parkcar.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoRepository repository;

    public VeiculoController(VeiculoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody VeiculoRequestDTO veiculoDto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setNomeProprietario(veiculoDto.getNomeProprietario());
        veiculo.setTipo(veiculoDto.getTipo());

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.save(veiculo))
                .statusCode(HttpStatus.CREATED.value())
                .build());
    }

    @GetMapping
    public ResponseEntity listar() {

        return ResponseEntity.ok(ResponseDTO.builder()
                .data(repository.findAll())
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @GetMapping("/{placa}")
    public ResponseEntity buscarPorPlaca(@PathVariable String placa) {

        return repository.findByPlaca(placa)
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
