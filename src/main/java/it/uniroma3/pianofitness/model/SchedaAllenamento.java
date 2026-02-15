package it.uniroma3.pianofitness.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SchedaAllenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titolo;

    @NotBlank
    @Column(length = 2000)
    private String descrizione;

    @NotBlank
    private String difficolta;

    @Column(nullable = false)
    private LocalDate dataCreazione = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Utente autore;

    @OneToMany(mappedBy = "scheda", cascade = CascadeType.ALL)
    private List<RigaScheda> righeScheda;

    @OneToMany(mappedBy = "scheda", cascade = CascadeType.ALL)
    private List<Recensione> recensioni;

}
