package com.example.ac2.models;

import java.util.List;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "funcionarios")
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Column(length = 100, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "funcionarios", fetch = jakarta.persistence.FetchType.EAGER)
    @ToString.Exclude
    private List<Projeto> projetos;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "setor_id")
    private Setor setor;

}
