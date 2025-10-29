package com.example.ac2.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;
import com.example.ac2.services.ProjetoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {
    
    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarProjeto(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        projetoService.salvar(projetoRequestDTO);
    }
    
    @GetMapping("/{id}")
    public ProjetoDTO obterPorId(@PathVariable Integer id) {
        return projetoService.obterPorId(id);
    }

    @PostMapping("{idProjeto}/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
        
    }
    
    @GetMapping
    public java.util.List<ProjetoDTO> listarProjetos() {
        return projetoService.findAll();
    }

    @GetMapping("/buscar-por-data")
    public List<ProjetoDTO> findByDateRange(
        @RequestParam("inicio")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataInicio,
        @RequestParam("fim")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate dataFim) {
            return projetoService.findByDateRange(dataInicio, dataFim);
        }
}
