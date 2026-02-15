package it.uniroma3.pianofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.pianofitness.model.Esercizio;

public interface EsercizioRepository extends JpaRepository<Esercizio, Long> {

    List<Esercizio> findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCaseOrMuscoloTargetContainingIgnoreCase(
            String nome, String categoria, String muscoloTarget);
}
