package it.uniroma3.pianofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.pianofitness.model.RigaScheda;
import it.uniroma3.pianofitness.model.SchedaAllenamento;

public interface RigaSchedaRepository extends JpaRepository<RigaScheda, Long> {

    List<RigaScheda> findAllByScheda(SchedaAllenamento scheda);
}
