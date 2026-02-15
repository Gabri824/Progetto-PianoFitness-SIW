package it.uniroma3.pianofitness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.pianofitness.model.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByEmail(String email);

    boolean existsByEmail(String email);

    long countByRole(String role);
}
