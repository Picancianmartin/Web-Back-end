package com.example.ac1p2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ac1p2.repositories.DiretorRepository;
import com.example.ac1p2.repositories.FilmeRepository;
import com.example.ac1p2.models.Diretor;
import com.example.ac1p2.models.Filme;

@SpringBootApplication
public class Ac1p2Application {

	@Bean
public CommandLineRunner init(@Autowired FilmeRepository filmeRepository, @Autowired DiretorRepository diretorRepository) {
    return args -> {
        
        Diretor diretor1 = diretorRepository.save(new Diretor(null, "John Smith", null));
        Diretor diretor2 = diretorRepository.save(new Diretor(null, "Jean Cocteau", null));
        Diretor diretor3 = diretorRepository.save(new Diretor(null, "Joseph Meaven", null));

        Filme filme1 = filmeRepository.save (new Filme(null, "E o vento levou", 2380, diretor1));
        Filme filme2 = filmeRepository.save (new Filme(null, "Esse poderoso chefão", 1750, diretor1));
        Filme filme3 = filmeRepository.save (new Filme(null, "Batman", 1520, diretor2));
        Filme filme4 = filmeRepository.save (new Filme(null, "Superman", 1490, diretor2));
        Filme filme5 = filmeRepository.save (new Filme(null, "Homem de ferro", 1300, diretor3));

        diretor1.setFilmes(Arrays.asList(filme1, filme2));
        diretor2.setFilmes(Arrays.asList(filme3,filme4));
        diretor3.setFilmes(Arrays.asList(filme5));
        
        diretorRepository.save(diretor1);
        diretorRepository.save(diretor2);
        diretorRepository.save(diretor3);

        System.out.println("Filmes com duração maior que 1500: ");
        filmeRepository.findByDuracaoGreaterThan(1500).forEach(System.out::println);

        System.out.println("Filmes com duração menor ou igual a 1500: ");
        filmeRepository.findByDuracaoLessThanEqual(1500).forEach(System.out::println);

        System.out.println("Filme que começa com E: ");
        filmeRepository.findByTituloLike("E%").forEach(System.out::println);

        System.out.println("Diretores que o nome começa com 'Jo': ");
        diretorRepository.findByNomeStartingWith("Jo").forEach(System.out::println);

        System.out.println("Filmes do Diretor com id 2: ");
        Diretor diretor = diretorRepository.findByIdFetchFilmes(2);
		diretor.getFilmes().forEach(System.out::println);
    };
}

	public static void main(String[] args) {
		SpringApplication.run(Ac1p2Application.class, args);
	}

}
