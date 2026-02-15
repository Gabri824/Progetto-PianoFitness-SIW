package it.uniroma3.pianofitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.Esercizio;
import it.uniroma3.pianofitness.repository.EsercizioRepository;

@Service
public class EsercizioService {

    @Autowired
    private EsercizioRepository esercizioRepository;

    @Transactional
    public Esercizio saveEsercizio(Esercizio esercizio) {
        return this.esercizioRepository.save(esercizio);
    }

    @Transactional
    public Esercizio getEsercizio(Long id) {
        return this.esercizioRepository.findById(id).orElse(null);
    }

    @Transactional
    public long countEsercizi() {
        return this.esercizioRepository.count();
    }

    @Transactional
    public List<Esercizio> getAllEsercizi() {
        return this.esercizioRepository.findAll();
    }

    @Transactional
    public List<Esercizio> searchEsercizi(String keyword) {
        return this.esercizioRepository
                .findByNomeContainingIgnoreCaseOrCategoriaContainingIgnoreCaseOrMuscoloTargetContainingIgnoreCase(
                        keyword, keyword, keyword);
    }

    @Transactional
    public void deleteEsercizio(Long id) {
        this.esercizioRepository.deleteById(id);
    }
}
