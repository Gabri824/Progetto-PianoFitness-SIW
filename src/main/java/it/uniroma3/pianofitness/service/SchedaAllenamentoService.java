package it.uniroma3.pianofitness.service;

import java.util.Arrays;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.RigaScheda;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.repository.SchedaAllenamentoRepository;

@Service
public class SchedaAllenamentoService {

    @Autowired
    private SchedaAllenamentoRepository schedaAllenamentoRepository;

    @Transactional
    public SchedaAllenamento saveScheda(SchedaAllenamento scheda) {
        return this.schedaAllenamentoRepository.save(scheda);
    }

    @Transactional
    public SchedaAllenamento getScheda(Long id) {
        return this.schedaAllenamentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<SchedaAllenamento> getAllSchede() {
        return this.schedaAllenamentoRepository.findAll();
    }

    @Transactional
    public List<SchedaAllenamento> getAllSchedeByAutore(Utente autore) {
        return this.schedaAllenamentoRepository.findByAutore(autore);
    }

    @Transactional
    public List<SchedaAllenamento> getAllSchedeByRole(String role) {
        return this.schedaAllenamentoRepository.findByRole(role);
    }

    @Transactional
    public void deleteScheda(Long id) {
        this.schedaAllenamentoRepository.deleteById(id);
    }

    @Transactional
    public long countSchede() {
        return this.schedaAllenamentoRepository.count();
    }

    @Transactional
    public long countSchedeByAutore(Utente autore) {
        return this.schedaAllenamentoRepository.countByAutore(autore);
    }

    @Transactional
    public long countSchedeByRole(String role) {
        return this.schedaAllenamentoRepository.countByRole(role);
    }

    @Transactional
    public Map<String, List<RigaScheda>> getSchedaDivisaPerGiorni(Long schedaId) {
        SchedaAllenamento scheda = this.getScheda(schedaId);
        if (scheda == null) {
            return null;
        }

        // 1. Raggruppa i dati in una mappa non ordinata (o hash map)
        Map<String, List<RigaScheda>> mappaNonOrdinata = scheda.getRigheScheda().stream()
                .collect(Collectors.groupingBy(RigaScheda::getGiorno));

        // 2. Definisci l'ordine desiderato
        List<String> giorniOrdinati = Arrays.asList("Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato",
                "Domenica");

        // 3. Crea una nuova mappa LinkedHashMap per mantenere l'ordine di inserimento
        Map<String, List<RigaScheda>> mappaOrdinata = new LinkedHashMap<>();

        // 4. Inserisci i giorni nell'ordine corretto se presenti
        for (String giorno : giorniOrdinati) {
            if (mappaNonOrdinata.containsKey(giorno)) {
                mappaOrdinata.put(giorno, mappaNonOrdinata.get(giorno));
                mappaNonOrdinata.remove(giorno);
            }
        }

        return mappaOrdinata;
    }

    @Transactional
    public List<SchedaAllenamento> searchSchede(String keyword) {
        return this.schedaAllenamentoRepository
                .findByTitoloContainingIgnoreCaseOrDifficoltaContainingIgnoreCase(keyword, keyword);
    }

    public boolean isOwner(Long schedaId, String username) {
        SchedaAllenamento scheda = getScheda(schedaId);
        if (scheda == null || scheda.getAutore() == null)
            return false;

        return scheda.getAutore().getCredentials().getEmail().equals(username);
    }
}
