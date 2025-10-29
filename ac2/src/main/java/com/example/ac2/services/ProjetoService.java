package com.example.ac2.services;

import java.time.LocalDate;
import java.util.List;

import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;

public interface ProjetoService {

    void salvar(ProjetoRequestDTO projetoRequestDTO);

    ProjetoDTO obterPorId(Integer id);

    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);

    List<ProjetoDTO> findAll();

    List<ProjetoDTO> findByDateRange(LocalDate dataInicio, LocalDate dataFim);

}
