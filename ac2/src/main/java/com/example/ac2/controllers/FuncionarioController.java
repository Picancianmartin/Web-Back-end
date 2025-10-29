package com.example.ac2.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.services.FuncionarioService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;

    @PostMapping
    public void adicionarFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        funcionarioService.salvar(funcionarioRequestDTO);
    }
    
    @GetMapping("{idFuncionario}/projetos")
    public List<ProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) {
    return funcionarioService.buscarProjetosPorFuncionario(idFuncionario);
    }   
    
}
