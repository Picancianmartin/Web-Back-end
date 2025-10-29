package com.example.ac2.services;

import java.util.List;


import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO); 


    List<ProjetoDTO> buscarProjetosPorFuncionario(Integer idFuncionario);
    
}
