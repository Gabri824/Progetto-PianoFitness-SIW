package it.uniroma3.pianofitness.controller;

import java.util.List;

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
import it.uniroma3.pianofitness.model.Esercizio;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.EsercizioService;

@Controller
public class EsercizioController {

    @Autowired
    private EsercizioService esercizioService;

    @Autowired
    private CredentialsService credentialsService;

    @GetMapping("/esercizi")
    public String listaEsercizi(Model model, @RequestParam(required = false) String keyword) {
        List<Esercizio> esercizi;
        if (keyword != null && !keyword.isEmpty()) {
            esercizi = this.esercizioService.searchEsercizi(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            esercizi = this.esercizioService.getAllEsercizi();
        }
        model.addAttribute("esercizi", esercizi);
        return "esercizio/elencoEsercizi";
    }

    @GetMapping("/esercizio/{id}")
    public String getEsercizio(@PathVariable("id") Long id, @RequestParam(required = false) String provenienza,
            @RequestParam(required = false) Long schedaId, Model model) {
        Esercizio esercizio = this.esercizioService.getEsercizio(id);
        if (esercizio == null) {
            model.addAttribute("messaggioErrore", "Esercizio non trovato!");
            return "redirect:/esercizi";
        }
        model.addAttribute("esercizio", esercizio);
        model.addAttribute("provenienza", provenienza);
        model.addAttribute("schedaId", schedaId);
        return "esercizio/dettaglioEsercizio";
    }

    @GetMapping("/formEsercizio")
    public String showFormNewEsercizio(Model model) {
        model.addAttribute("esercizio", new Esercizio());
        return "esercizio/formEsercizio";
    }

    @PostMapping("/formEsercizio")
    public String createEsercizio(@ModelAttribute("esercizio") Esercizio esercizio, Model model,
            RedirectAttributes redirectAttributes) {
        if (esercizio.getNome() == null && esercizio.getNome().trim().isEmpty()) {
            model.addAttribute("messaggioErrore", "Nome non valido per l'esercizio");
            return "esercizio/formEsercizio";
        }

        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai i permessi di amministratore per creare esercizi.");
            return "redirect:/esercizi";
        }

        this.esercizioService.saveEsercizio(esercizio);
        return "redirect:/esercizi";

    }

    @GetMapping("/esercizio/delete/{id}")
    public String deleteEsercizio(@PathVariable("id") Long idEsercizio, Model model,
            RedirectAttributes redirectAttributes) {
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai i permessi di amministratore per cancellare un esercizio.");
            return "redirect:/esercizio/" + idEsercizio;
        }

        this.esercizioService.deleteEsercizio(idEsercizio);
        return "redirect:/esercizi";
    }

    @GetMapping("/esercizio/edit/{id}")
    public String showFormModificaEsercizio(@PathVariable("id") Long idEsercizio, Model model,
            RedirectAttributes redirectAttributes) {
        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai i permessi di amministratore per modificare un esercizio.");
            return "redirect:/esercizio/" + idEsercizio;
        }

        model.addAttribute("esercizio", this.esercizioService.getEsercizio(idEsercizio));
        return "esercizio/formModificaEsercizio";
    }

    @PostMapping("/esercizio/edit/{id}")
    public String updateEsercizio(@PathVariable("id") Long idEsercizio,
            @ModelAttribute("esercizio") Esercizio esercizioForm, Model model, RedirectAttributes redirectAttributes) {
        Esercizio esercizioEsistente = this.esercizioService.getEsercizio(idEsercizio);
        if (esercizioEsistente == null) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Esercizio non trovato!");
            return "redirect:/esercizi";
        }

        if (!isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non hai i permessi di amministratore per modificare un esercizio.");
            return "redirect:/esercizio/" + idEsercizio;
        }

        esercizioEsistente.setNome(esercizioForm.getNome());
        esercizioEsistente.setCategoria(esercizioForm.getCategoria());
        esercizioEsistente.setMuscoloTarget(esercizioForm.getMuscoloTarget());
        esercizioEsistente.setDescrizione(esercizioForm.getDescrizione());

        this.esercizioService.saveEsercizio(esercizioEsistente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Esercizio modificato con successo!");
        return "redirect:/esercizio/" + idEsercizio;

    }

    private boolean isAdmin() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());
        return credentials.getRole().equals(Credentials.ADMIN_ROLE);
    }
}
