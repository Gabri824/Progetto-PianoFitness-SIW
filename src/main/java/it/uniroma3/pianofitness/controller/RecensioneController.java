
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.model.Recensione;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.RecensioneService;
import it.uniroma3.pianofitness.service.SchedaAllenamentoService;

@Controller
public class RecensioneController {

    @Autowired
    private RecensioneService recensioneService;

    @Autowired
    private SchedaAllenamentoService schedaAllenamentoService;

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/scheda/{id}/recensioni")
    public String listaRecensioni(Model model) {
        model.addAttribute("recensioni", this.recensioneService.getAllRecensioni());
        return "recensioni";
    }

    @GetMapping("/scheda/{schedaId}/formRecensione")
    public String formRecensione(@PathVariable("schedaId") Long schedaId, Model model) {
        model.addAttribute("recensione", new Recensione());
        model.addAttribute("schedaId", schedaId);
        return "recensione/formRecensione";
    }

    @PostMapping("/scheda/{schedaId}/formRecensione")
    public String createRecensione(@PathVariable("schedaId") Long schedaId,
            @ModelAttribute("recensione") Recensione recensione, Model model, RedirectAttributes redirectAttributes) {
        SchedaAllenamento scheda = this.schedaAllenamentoService.getScheda(schedaId);
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai permessi di amministratore per scrivere recensioni.");
            return "redirect:/scheda/" + schedaId;
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());
        Utente autore = credentials.getUtente();
        if (recensione.getValutazione() < 1 || recensione.getValutazione() > 5) {
            model.addAttribute("messaggioErrore", "La valutazione deve essere compresa tra 1 e 5.");
            model.addAttribute("schedaId", schedaId);
            return "recensione/formRecensione";
        }

        try {
            this.recensioneService.saveRecensione(recensione, autore, scheda);
        } catch (Exception e) {
            model.addAttribute("messaggioErrore", "Errore durante il salvataggio della recensione. Riprova.");
            model.addAttribute("scheda", scheda);
            model.addAttribute("schedaId", schedaId);
            return "recensione/formRecensione";
        }
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Recensione salvata con successo!");
        return "redirect:/scheda/" + schedaId;

    }

    @GetMapping("/scheda/{schedaId}/recensione/delete/{recensioneId}")
    public String deleteRecensione(@PathVariable("schedaId") Long schedaId,
            @PathVariable("recensioneId") Long recensioneId,
            @RequestParam(required = false) Long fromProfileId, Model model, RedirectAttributes redirectAttributes) {
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai permessi di amministratore per eliminare recensioni.");
            return "redirect:/scheda/" + schedaId;
        }
        this.recensioneService.deleteRecensione(recensioneId);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Recensione eliminata con successo!");
        if (fromProfileId != null) {
            return "redirect:/utente/" + fromProfileId;
        }
        return "redirect:/scheda/" + schedaId;
    }

    @GetMapping("/scheda/{schedaId}/recensione/edit/{recensioneId}")
    public String editRecensione(@PathVariable("schedaId") Long schedaId,
            @PathVariable("recensioneId") Long recensioneId,
            @RequestParam(required = false) Long fromProfileId, Model model, RedirectAttributes redirectAttributes) {
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai permessi di amministratore per modificare recensioni.");
            return "redirect:/scheda/" + schedaId;
        }
        Recensione recensione = this.recensioneService.getRecensione(recensioneId);
        model.addAttribute("recensione", recensione);
        return "recensione/formModificaRecensione";
    }

    @PostMapping("/scheda/{schedaId}/recensione/edit/{recensioneId}")
    public String updateRecensione(@PathVariable("schedaId") Long schedaId,
            @PathVariable("recensioneId") Long recensioneId,
            @ModelAttribute("recensione") Recensione recensioneForm, Model model,
            RedirectAttributes redirectAttributes) {
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai permessi di amministratore per modificare recensioni.");
            return "redirect:/scheda/" + schedaId;
        }
        Recensione recensioneEsistente = this.recensioneService.getRecensione(recensioneId);
        recensioneEsistente.setTesto(recensioneForm.getTesto());
        recensioneEsistente.setValutazione(recensioneForm.getValutazione());
        this.recensioneService.saveRecensione(recensioneEsistente, recensioneEsistente.getAutore(),
                recensioneEsistente.getScheda());
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Recensione modificata con successo!");
        return "redirect:/scheda/" + schedaId;
    }

    private boolean isAdmin() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());
        return credentials.getRole().equals(Credentials.ADMIN_ROLE);
    }
}
