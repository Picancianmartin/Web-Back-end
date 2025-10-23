package com.example.ac2.services;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;

public interface ProjetoService {

    void salvar(ProjetoRequestDTO projetoRequestDTO);

    ProjetoDTO obterPorId(Integer id);

    void vincularFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, Integer id);

    
}
