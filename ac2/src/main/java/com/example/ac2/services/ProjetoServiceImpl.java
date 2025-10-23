package com.example.ac2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2.dtos.FuncionarioDTO;
import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.ProjetoDTO;
import com.example.ac2.dtos.ProjetoRequestDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;
    private FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void salvar(ProjetoRequestDTO projetoRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(projetoRequestDTO.getIdFuncionario())
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

        Projeto projeto = new Projeto();
        projeto.setDescricao(projetoRequestDTO.getDescricao());
        projeto.setDataInicio(projetoRequestDTO.getDataInicio());
        projeto.setDataFim(projetoRequestDTO.getDataFim());
        projeto.setFuncionarios(List.of(funcionario));
        projetoRepository.save(projeto);
    }

    @Override
    public ProjetoDTO obterPorId(Integer id) {
        return projetoRepository.findById(id)
                .map((Projeto p) -> {
                    return ProjetoDTO.builder()
                    .id(p.getId())
                    .descricao(p.getDescricao())
                    .dataInicio(p.getDataInicio())
                    .dataFim(p.getDataFim())
                    .funcionarios(FuncionarioDTO.builder()
                        .id(p.getFuncionarios().get(0).getId())
                        .nome(p.getFuncionarios().get(0).getNome())
                        .build()
                    )
                .build();
                }) 
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
    }

    @Override
    public void vincularFuncionario(FuncionarioRequestDTO funcionarioRequestDTO, Integer id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
    }


}
