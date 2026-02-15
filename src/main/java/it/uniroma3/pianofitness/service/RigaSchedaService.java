package it.uniroma3.pianofitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.RigaScheda;
import it.uniroma3.pianofitness.repository.RigaSchedaRepository;

@Service
public class RigaSchedaService {

    @Autowired
    private RigaSchedaRepository rigaSchedaRepository;

    @Transactional
    public void saveRigaScheda(RigaScheda rigaScheda) {
        this.rigaSchedaRepository.save(rigaScheda);
    }

    @Transactional
    public void deleteRigaScheda(Long id) {
        this.rigaSchedaRepository.deleteById(id);
    }

    @Transactional
    public RigaScheda getRigaScheda(Long id) {
        return this.rigaSchedaRepository.findById(id).orElse(null);
    }

    public boolean isOwner(Long rigaId, String username) {
        RigaScheda riga = getRigaScheda(rigaId);
        if (riga == null || riga.getScheda() == null)
            return false;

        // Risale dalla Riga -> Scheda -> Autore -> Credentials -> Email
        return riga.getScheda().getAutore().getCredentials().getEmail().equals(username);
    }

}
