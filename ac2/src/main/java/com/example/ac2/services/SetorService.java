package com.example.ac2.services;

import java.util.List;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;

public interface SetorService {


    void salvar(SetorRequestDTO setorRequestDTO);

    SetorDTO obterPorId(Integer id);
    
    List<SetorDTO> findAll();
}
    

