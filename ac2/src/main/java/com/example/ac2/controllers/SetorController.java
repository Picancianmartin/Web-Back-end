package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;
import com.example.ac2.services.SetorService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/setores")
@RequiredArgsConstructor
public class SetorController {

    private final SetorService setorService;
    

    @PostMapping
    public void salvar(@RequestBody SetorRequestDTO setorRequestDTO) {
        setorService.salvar(setorRequestDTO);
    }

    @GetMapping("/{id}")
    public SetorDTO obterPorId(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }

    @GetMapping
    public java.util.List<SetorDTO> findAll() {
        return setorService.findAll();
    }
    
    
    
}
