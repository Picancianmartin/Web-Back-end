package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;
import com.example.ac2.services.ProjetoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    
    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public void adicionarProjeto(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        projetoService.salvar(projetoRequestDTO);
    }
    
    @GetMapping("{id}")
    public ProjetoDTO obterPorId(@PathVariable Integer id) {
        return projetoService.obterPorId(id);
    }

    @PostMapping("{id}")
    public void vincularFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, @PathVariable Integer id) {
        projetoService.vincularFuncionario(funcionarioRequestDTO, id);
        
    }
    
    
}
