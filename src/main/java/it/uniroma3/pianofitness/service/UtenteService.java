package it.uniroma3.pianofitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Transactional
    public Utente saveUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    @Transactional
    public Utente getUtente(Long id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public List<Utente> searchUtenti(String keyword) {
        return utenteRepository.findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(keyword, keyword);
    }

    @Transactional
    public long countUtenti() {
        return utenteRepository.count();
    }

}
