package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;
import com.example.ac2.services.SetorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/setores")
public class SetorController {

    private SetorService setorService;
    
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public void adicionarSetor(@RequestBody SetorRequestDTO setorRequestDTO) {
        setorService.salvar(setorRequestDTO);
    }

    @GetMapping("{id}")
    public SetorDTO obterPorId(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }

    
    
    
    
}
