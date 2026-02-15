package it.uniroma3.pianofitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.model.RigaScheda;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.EsercizioService;
import it.uniroma3.pianofitness.service.RigaSchedaService;
import it.uniroma3.pianofitness.service.SchedaAllenamentoService;

@Controller
public class RigaSchedaController {
    @Autowired
    private RigaSchedaService rigaSchedaService;

    @Autowired
    private SchedaAllenamentoService schedaService;

    @Autowired
    private EsercizioService esercizioService;

    @Autowired
    private CredentialsService credentialsService;

    /**
     * Gestisce l'aggiunta di un nuovo esercizio alla scheda.
     * Viene chiamato dal form presente in fondo alla pagina "dettaglioScheda".
     */
    @PostMapping("/scheda/{schedaId}/addRiga")
    public String addRiga(@PathVariable("schedaId") Long schedaId, @ModelAttribute("nuovaRiga") RigaScheda riga,
            Model model, RedirectAttributes redirectAttributes) {

        SchedaAllenamento scheda = schedaService.getScheda(schedaId);

        if (!isMine(scheda) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non hai i permessi per modificare questa scheda.");
            return "redirect:/scheda/" + schedaId;
        }

        if (riga.getEsercizio() == null) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Devi selezionare un esercizio valido.");
            return "redirect:/scheda/" + schedaId;
        }

        riga.setScheda(scheda);
        rigaSchedaService.saveRigaScheda(riga);

        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Esercizio aggiunto correttamente!");
        return "redirect:/scheda/" + schedaId;
    }

    /**
     * Gestisce la rimozione di un esercizio.
     */
    @GetMapping("/scheda/{schedaId}/deleteRiga/{rigaId}")
    public String deleteRiga(@PathVariable("schedaId") Long schedaId, @PathVariable("rigaId") Long rigaId,
            RedirectAttributes redirectAttributes) {

        RigaScheda riga = rigaSchedaService.getRigaScheda(rigaId);

        if (riga == null) {
            return "redirect:/scheda/" + schedaId;
        }

        // Controllo Sicurezza
        if (!isMine(riga.getScheda()) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non puoi cancellare questo esercizio!");
            return "redirect:/scheda/" + schedaId;
        }

        rigaSchedaService.deleteRigaScheda(rigaId);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Esercizio rimosso.");
        return "redirect:/scheda/" + schedaId;
    }

    @GetMapping("/riga/edit/{id}")
    public String formModificaRiga(@PathVariable("id") Long rigaId, Model model,
            RedirectAttributes redirectAttributes) {
        RigaScheda riga = rigaSchedaService.getRigaScheda(rigaId);

        if (riga == null) {
            return "redirect:/schede";
        }

        // Controllo Sicurezza: Solo autore o admin possono modificare
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!rigaSchedaService.isOwner(rigaId, username) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non puoi modificare questa riga!");
            return "redirect:/scheda/" + riga.getScheda().getId();
        }

        model.addAttribute("rigaScheda", riga);
        // Serve la lista esercizi per la select, proprio come nell'aggiunta
        model.addAttribute("listaEsercizi", this.esercizioService.getAllEsercizi());

        return "scheda/formModificaRiga";
    }

    @PostMapping("/riga/edit/{id}")
    public String updateRiga(@PathVariable("id") Long rigaId, @ModelAttribute("rigaScheda") RigaScheda rigaForm,
            Model model, RedirectAttributes redirectAttributes) {

        RigaScheda rigaEsistente = rigaSchedaService.getRigaScheda(rigaId);
        Long idSchedaPadre = rigaEsistente.getScheda().getId();

        // Controllo Sicurezza
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!rigaSchedaService.isOwner(rigaId, username) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non puoi modificare questa riga!");
            return "redirect:/scheda/" + idSchedaPadre;
        }

        // Aggiorniamo i campi
        rigaEsistente.setGiorno(rigaForm.getGiorno());
        rigaEsistente.setEsercizio(rigaForm.getEsercizio());
        rigaEsistente.setSerie(rigaForm.getSerie());
        rigaEsistente.setRipetizioni(rigaForm.getRipetizioni());
        rigaEsistente.setTempoRiposoSecondi(rigaForm.getTempoRiposoSecondi());
        rigaEsistente.setNote(rigaForm.getNote());

        rigaSchedaService.saveRigaScheda(rigaEsistente);

        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Esercizio modificato correttamente!");

        return "redirect:/scheda/" + idSchedaPadre;
    }

    // --- Metodi di Supporto per la sicurezza ---

    private Utente getUtenteCorrente() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());
        return credentials.getUtente();
    }

    private boolean isAdmin() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());
        return credentials.getRole().equals(Credentials.ADMIN_ROLE);
    }

    private boolean isMine(SchedaAllenamento scheda) {
        return scheda.getAutore().getId().equals(getUtenteCorrente().getId());
    }
}
