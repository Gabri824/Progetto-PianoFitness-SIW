package it.uniroma3.pianofitness.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RigaScheda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String giorno;

    @NotNull
    @Min(1)
    private Integer serie;

    @NotNull
    @Min(1)
    private Integer ripetizioni;

    @NotNull
    @Min(0)
    private Integer tempoRiposoSecondi;

    @Column(length = 2000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "esercizio_id", nullable = false)
    private Esercizio esercizio;

    @ManyToOne
    @JoinColumn(name = "scheda_id", nullable = false)
    private SchedaAllenamento scheda;

}
