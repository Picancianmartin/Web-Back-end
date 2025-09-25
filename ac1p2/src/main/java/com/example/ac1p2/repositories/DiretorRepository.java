package com.example.ac1p2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac1p2.models.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {

    List<Diretor> findByNomeLike(String nome);

    List<Diretor> findByNomeStartingWith(String prefixo);


    @Query("SELECT d FROM Diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
    Diretor findByIdFetchFilmes(long id);

    
}
