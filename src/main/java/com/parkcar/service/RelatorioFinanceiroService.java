package com.parkcar.service;

import com.parkcar.dto.LucroPorTipoDTO;
import com.parkcar.repository.HistoricoEstacionamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioFinanceiroService {

    private final HistoricoEstacionamentoRepository repository;

    public RelatorioFinanceiroService(HistoricoEstacionamentoRepository repository) {
        this.repository = repository;
    }

    public BigDecimal lucroTotalDoDia(LocalDate data) {
        return repository.lucroTotalPorDia(data);
    }

    public List<LucroPorTipoDTO> lucroPorTipo(LocalDate data) {
        return repository.lucroPorTipoNoDia(data);
    }
}