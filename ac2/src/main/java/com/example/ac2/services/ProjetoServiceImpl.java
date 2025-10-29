package com.example.ac2.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    

    @Override
    public void salvar(ProjetoRequestDTO projetoRequestDTO) {
        Projeto projeto = new Projeto();
        projeto.setDescricao(projetoRequestDTO.getDescricao());
        projeto.setDataInicio(projetoRequestDTO.getDataInicio());
        projeto.setDataFim(projetoRequestDTO.getDataFim());
        projetoRepository.save(projeto);
    }

    @Override
    public ProjetoDTO obterPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdFetchFuncionarios(id)
        .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado. ID: " + id));


        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(projeto.getId());
        projetoDTO.setDescricao(projeto.getDescricao());
        projetoDTO.setDataInicio(projeto.getDataInicio());
        projetoDTO.setDataFim(projeto.getDataFim());

        if (projeto.getFuncionarios() != null) {
            projetoDTO.setFuncionarios(
                projeto.getFuncionarios().stream().map(funcionario -> {
                    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
                    funcionarioDTO.setId(funcionario.getId());
                    funcionarioDTO.setNome(funcionario.getNome());
                    return funcionarioDTO;
                }).collect(Collectors.toList())

            );
        } return projetoDTO;
    }

    @Override
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado. ID: " + idProjeto));

        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
        .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado. ID: " + idFuncionario));

        projeto.getFuncionarios().add(funcionario);

        projetoRepository.save(projeto);
    }

    @Override
    public List<ProjetoDTO> findAll() {
        return projetoRepository.findAll().stream().map(projeto -> {
            ProjetoDTO projetoDTO = new ProjetoDTO();
            projetoDTO.setId(projeto.getId());
            projetoDTO.setDescricao(projeto.getDescricao());
            projetoDTO.setDataInicio(projeto.getDataInicio());
            projetoDTO.setDataFim(projeto.getDataFim());
            return projetoDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ProjetoDTO> findByDateRange(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new RegraNegocioException("As datas de início e fim devem ser fornecidas.");
        }

        List<Projeto> projetos = projetoRepository.findByDataInicioBetween(dataInicio, dataFim);

        return projetos.stream().map(projeto -> {
            ProjetoDTO projetoDTO = new ProjetoDTO();
            projetoDTO.setId(projeto.getId());
            projetoDTO.setDescricao(projeto.getDescricao());
            projetoDTO.setDataInicio(projeto.getDataInicio());
            projetoDTO.setDataFim(projeto.getDataFim());
            return projetoDTO;
        }).collect(Collectors.toList());
    }

}
