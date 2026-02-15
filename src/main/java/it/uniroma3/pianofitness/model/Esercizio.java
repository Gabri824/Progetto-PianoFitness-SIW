package it.uniroma3.pianofitness.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    private String categoria; // es. Cardio, Flessibilità, Corpo Libero, Macchinario, Manubri, etc

    @NotBlank
    private String muscoloTarget;

    @NotBlank
    @Column(length = 2000)
    private String descrizione;

}
