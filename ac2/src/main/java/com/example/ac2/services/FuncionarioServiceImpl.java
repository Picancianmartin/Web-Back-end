package com.example.ac2.services;

import java.util.List;

import com.example.ac2.dtos.FuncionarioRequestDTO;
import com.example.ac2.dtos.RegraNegocioException;
import com.example.ac2.models.Funcionario;
import com.example.ac2.models.Projeto;
import com.example.ac2.models.Setor;
import com.example.ac2.repositories.FuncionarioRepository;
import com.example.ac2.repositories.ProjetoRepository;
import com.example.ac2.repositories.SetorRepository;

public class FuncionarioServiceImpl implements FuncionarioService{
    
    private FuncionarioRepository funcionarioRepository;
    private ProjetoRepository projetoRepository;
    private SetorRepository setorRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, ProjetoRepository projetoRepository, SetorRepository setorRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    public void salvar(FuncionarioRequestDTO funcionarioRequestDTO) {
        Projeto projeto = projetoRepository.findById(funcionarioRequestDTO.getIdProjeto())
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        Setor setor = setorRepository.findById(funcionarioRequestDTO.getIdSetor())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setProjetos(List.of(projeto));
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<Funcionario> findByIdFetchProjetos(Integer id) {
        return funcionarioRepository.findByIdFetchProjetos(id);
    }
}
