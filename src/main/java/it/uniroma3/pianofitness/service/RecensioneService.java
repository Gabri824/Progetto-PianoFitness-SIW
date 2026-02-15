package it.uniroma3.pianofitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.Recensione;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.repository.RecensioneRepository;

@Service
public class RecensioneService {

    @Autowired
    private RecensioneRepository recensioneRepository;

    @Transactional
    public void saveRecensione(Recensione recensione, Utente autore, SchedaAllenamento scheda) {
        recensione.setAutore(autore);
        recensione.setScheda(scheda);
        this.recensioneRepository.save(recensione);
    }

    @Transactional
    public void deleteRecensione(Long id) {
        this.recensioneRepository.deleteById(id);
    }

    @Transactional
    public Recensione getRecensione(Long id) {
        return this.recensioneRepository.findById(id).orElse(null);
    }

    @Transactional
    public long countRecensioni() {
        return this.recensioneRepository.count();
    }

    @Transactional
    public List<Recensione> getAllRecensioni() {
        return this.recensioneRepository.findAll();
    }

    @Transactional
    public List<Recensione> getAllRecensioniByScheda(SchedaAllenamento scheda) {
        return this.recensioneRepository.findByScheda(scheda);
    }

    public boolean hasUserReviewed(SchedaAllenamento scheda, Utente autore) {
        return recensioneRepository.existsBySchedaAndAutore(scheda, autore);
    }
}
