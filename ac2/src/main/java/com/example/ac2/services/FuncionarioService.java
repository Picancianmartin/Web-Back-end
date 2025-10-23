package com.example.ac2.services;

import java.util.List;


import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.models.Funcionario;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO); 


    List<Funcionario> findByIdFetchProjetos(Integer id);
    
}
