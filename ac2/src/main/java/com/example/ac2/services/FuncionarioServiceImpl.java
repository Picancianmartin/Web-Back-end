package com.example.ac2.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.SetorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    @Override
    public void salvar(FuncionarioRequestDTO funcionarioRequestDTO) {

        Setor setor = setorRepository.findById(funcionarioRequestDTO.getIdSetor())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<ProjetoDTO> buscarProjetosPorFuncionario(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new RegraNegocioException("Funcionário não encontrado. ID: " + idFuncionario);
        }

        List<Projeto> projetos = funcionarioRepository.findProjetosByFuncionarioId(idFuncionario);

        return projetos.stream()
                .map(projeto -> {
                    ProjetoDTO projetoDTO = new ProjetoDTO();
                    projetoDTO.setId(projeto.getId());
                    projetoDTO.setDescricao(projeto.getDescricao());
                    projetoDTO.setDataInicio(projeto.getDataInicio());
                    projetoDTO.setDataFim(projeto.getDataFim());
                    return projetoDTO;
                }).collect(Collectors.toList());
    }
}
 