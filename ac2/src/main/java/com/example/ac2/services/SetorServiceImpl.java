package com.example.ac2.services;

import java.util.List;

import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.SetorRepository;

public class SetorServiceImpl {
    
    private SetorRepository setorRepository;
    private FuncionarioRepository funcionarioRepository;

    public SetorServiceImpl(SetorRepository setorRepository, FuncionarioRepository funcionarioRepository) {
        this.setorRepository = setorRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // @Override
    public void salvar (SetorRequestDTO setorRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(setorRequestDTO.getIdFuncionario())
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        Setor setor = new Setor();
        setor.setNome(setorRequestDTO.getNome());
        setor.setFuncionarios(List.of(funcionario));
        setorRepository.save(setor);
    }

    // @Override
    public SetorDTO obterPorId(Integer id) {
        return setorRepository.findById(id)
                .map((Setor s) -> {
                    return SetorDTO.builder()
                    .id(s.getId())
                    .nome(s.getNome())
                    .funcionarios(FuncionarioDTO.builder()
                        .id(s.getFuncionarios().get(0).getId())
                        .nome(s.getFuncionarios().get(0).getNome())
                        .build()
                    )
                .build();
                }) 
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));
    }
}
