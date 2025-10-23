package com.example.ac2.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.models.Funcionario;
import com.example.ac2.services.FuncionarioService;



@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public void adicionarFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        funcionarioService.salvar(funcionarioRequestDTO);
    }
    
    @GetMapping("{id}")
    public List<Funcionario> findByIdFetchProjetos(@PathVariable Integer id) {
    return funcionarioService.findByIdFetchProjetos(id);
    }   
    
}
