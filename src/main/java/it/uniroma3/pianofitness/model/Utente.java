package it.uniroma3.pianofitness.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;

    private LocalDate dataNascita;

    @OneToOne(mappedBy = "utente")
    private Credentials credentials;

    private boolean banned = false;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    private List<SchedaAllenamento> schedeCreate;

}
