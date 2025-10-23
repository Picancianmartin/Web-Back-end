package com.example.ac2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2.models.Projeto;

import java.time.LocalDate;
import java.util.List;


public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    List<Projeto> findByIdFetchFuncionarios(Integer id);

    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);

}
