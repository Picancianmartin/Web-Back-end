package com.example.ac2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.SetorDTO;
import com.example.ac2.dtos.SetorRequestDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    @Override
    public void salvar(SetorRequestDTO setorRequestDTO) {
        if (setorRequestDTO.getNome() == null || setorRequestDTO.getNome().isEmpty()) {
            throw new RegraNegocioException("Nome do setor não pode ser vazio");
        }
        Setor setor = new Setor();
        setor.setNome(setorRequestDTO.getNome());
        setorRepository.save(setor);
    }

    @Override
    public List<SetorDTO> findAll() {
        List<Setor> setores = setorRepository.findAllWithFuncionarios();

       return setores.stream().map (setor -> {

        SetorDTO setorDTO = new SetorDTO();
        setorDTO.setId(setor.getId());
        setorDTO.setNome(setor.getNome());

        if (setor.getFuncionarios() != null) {
            setorDTO.setFuncionarios(
                    setor.getFuncionarios().stream().map(funcionario -> {
                        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
                        funcionarioDTO.setId(funcionario.getId());
                        funcionarioDTO.setNome(funcionario.getNome());
                        return funcionarioDTO;
                    }).collect(Collectors.toList()));
        }
        return setorDTO;
    }).collect(Collectors.toList());
    }

    @Override
    public SetorDTO obterPorId(Integer id) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        SetorDTO setorDTO = new SetorDTO();
        setorDTO.setId(setor.getId());
        setorDTO.setNome(setor.getNome());

        if (setor.getFuncionarios() != null) {
            setorDTO.setFuncionarios(
                    setor.getFuncionarios().stream().map(funcionario -> {
                        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
                        funcionarioDTO.setId(funcionario.getId());
                        funcionarioDTO.setNome(funcionario.getNome());
                        return funcionarioDTO;
                    }).collect(Collectors.toList()));
        }
        return setorDTO;
    }
}
