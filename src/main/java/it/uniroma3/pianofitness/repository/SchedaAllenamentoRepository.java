package it.uniroma3.pianofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;

public interface SchedaAllenamentoRepository extends JpaRepository<SchedaAllenamento, Long> {

    List<SchedaAllenamento> findByAutore(Utente autore);

    long countByAutore(Utente autore);

    List<SchedaAllenamento> findByTitoloContainingIgnoreCaseOrDifficoltaContainingIgnoreCase(String titolo,
            String difficolta);

    @Query("SELECT count(s) FROM SchedaAllenamento s WHERE s.autore.credentials.role = :role")
    long countByRole(@Param("role") String role);

    List<SchedaAllenamento> findByTitoloContainingIgnoreCaseOrDescrizioneContainingIgnoreCaseOrDifficoltaContainingIgnoreCase(
            String titolo, String descrizione, String difficolta);

    @Query("SELECT s FROM SchedaAllenamento s WHERE s.autore.credentials.role = :role")
    List<SchedaAllenamento> findByRole(@Param("role") String role);
}
