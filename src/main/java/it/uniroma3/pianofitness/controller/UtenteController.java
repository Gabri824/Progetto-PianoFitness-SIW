package it.uniroma3.pianofitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.UtenteService;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private it.uniroma3.pianofitness.service.SchedaAllenamentoService schedaService;

    @GetMapping("/profilo")
    public String profilo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

        return "redirect:/utente/" + credentials.getUtente().getId();
    }

    @GetMapping("/utente/{id}")
    public String getUtente(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Utente utente = this.utenteService.getUtente(id);
        if (utente == null) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Utente non trovato!");
            return "redirect:/";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/login";
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

        boolean isAdmin = credentials.getRole().equals(Credentials.ADMIN_ROLE);
        boolean isOwner = credentials.getUtente().getId().equals(id);

        if (!isOwner && !isAdmin) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai i permessi per visualizzare questo profilo.");
            return "redirect:/"; // Or redirect to own profile
        }

        model.addAttribute("utente", utente);
        model.addAttribute("schedeCreate", schedaService.getAllSchedeByAutore(utente));
        return "utente/profilo";
    }

    @GetMapping("/utente/{id}/schede")
    public String getSchedeUtente(@PathVariable("id") Long idUtente, Model model) {
        model.addAttribute("schede", this.utenteService.getUtente(idUtente).getSchedeCreate());
        return "utente/schedeUtente";
    }

    @GetMapping("/utente/edit/{id}")
    public String showFormModificaUtente(@PathVariable("id") Long idUtente, Model model) {
        model.addAttribute("utente", this.utenteService.getUtente(idUtente));
        return "utente/formModificaUtente";
    }

    @PostMapping("/utente/edit/{id}")
    public String updateUtente(@PathVariable("id") Long idUtente, @ModelAttribute("utente") Utente utenteForm,
            Model model, RedirectAttributes redirectAttributes) {
        Utente utenteEsistente = this.utenteService.getUtente(idUtente);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

        if (!credentials.getUtente().getId().equals(idUtente)) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non puoi modificare il profilo di un altro utente.");
            return "redirect:/";
        }
        utenteEsistente.setNome(utenteForm.getNome());
        utenteEsistente.setCognome(utenteForm.getCognome());
        utenteEsistente.setDataNascita(utenteForm.getDataNascita());

        this.utenteService.saveUtente(utenteEsistente);

        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Profilo aggiornato con successo!");

        return "redirect:/utente/" + idUtente;
    }
}
