package it.uniroma3.pianofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.pianofitness.model.Recensione;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {

    List<Recensione> findByScheda(SchedaAllenamento scheda);

    List<Recensione> findByAutore(Utente autore);

    boolean existsBySchedaAndAutore(SchedaAllenamento scheda, Utente autore);
}
