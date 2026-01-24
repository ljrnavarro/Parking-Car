package com.parkcar.service;

import com.parkcar.domain.entity.*;
import com.parkcar.dto.ResponseDTO;
import com.parkcar.dto.VeiculoRequestDTO;
import com.parkcar.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final EstacionamentoRepository estacionamentoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, EstacionamentoRepository estacionamentoRepository){
        this.veiculoRepository = veiculoRepository;
        this.estacionamentoRepository = estacionamentoRepository;
    }

    public List<Veiculo> Listar(){
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> BuscarPorPlaca(String placa){
        return veiculoRepository.findByPlaca(placa);
    }

    public Veiculo Criar(VeiculoRequestDTO veiculoDto){

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setNomeProprietario(veiculoDto.getNomeProprietario());
        veiculo.setTipo(veiculoDto.getTipo());

        return veiculoRepository.save(veiculo);
    }

    public Boolean Deletar(Long id)
    {
        var retVeiculo = veiculoRepository.findById(id);

        if (!retVeiculo.isEmpty()){
            var retEstacionamento = estacionamentoRepository.findByVeiculo_Placa(retVeiculo.get().getPlaca());

            if (!retEstacionamento.isEmpty())
                throw (new IllegalArgumentException("Veículo ja foi Estacionado"));
        }

        veiculoRepository.deleteById(retVeiculo.get().getId());
        return true;
    }
}
