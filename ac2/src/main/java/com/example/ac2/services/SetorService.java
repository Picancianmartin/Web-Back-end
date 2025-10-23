package com.example.ac2.services;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;

public interface SetorService {


    void salvar(SetorRequestDTO setorRequestDTO);

    public SetorDTO obterPorId(Integer id);
    
}
    

