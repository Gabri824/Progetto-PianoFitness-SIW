package it.uniroma3.pianofitness.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 2000)
    private String testo;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer valutazione;

    private LocalDate dataCreazione = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Utente autore;

    @ManyToOne
    @JoinColumn(name = "scheda_id", nullable = false)
    private SchedaAllenamento scheda;

}
