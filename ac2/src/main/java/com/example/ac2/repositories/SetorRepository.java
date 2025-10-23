package com.example.ac2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ac2.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    
    List<Setor> allList();


}
