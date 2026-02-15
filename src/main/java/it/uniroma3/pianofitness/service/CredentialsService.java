package it.uniroma3.pianofitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.repository.CredentialsRepository;

@Service
public class CredentialsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    @Transactional
    public Credentials updateCredentials(Credentials credentials) {
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    @Transactional
    public Credentials getCredentials(Long id) {
        return this.credentialsRepository.findById(id).orElse(null);
    }

    @Transactional
    public Credentials getCredentialsByEmail(String email) {
        return this.credentialsRepository.findByEmail(email).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return this.credentialsRepository.existsByEmail(email);
    }

    public long countByRole(String role) {
        return this.credentialsRepository.countByRole(role);
    }
}
