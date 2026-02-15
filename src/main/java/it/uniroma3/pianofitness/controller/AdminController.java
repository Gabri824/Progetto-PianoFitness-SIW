package it.uniroma3.pianofitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.EsercizioService;
import it.uniroma3.pianofitness.service.RecensioneService;
import it.uniroma3.pianofitness.service.SchedaAllenamentoService;
import it.uniroma3.pianofitness.service.UtenteService;

@Controller
public class AdminController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private SchedaAllenamentoService schedaAllenamentoService;

    @Autowired
    private EsercizioService esercizioService;

    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalUtenti", this.utenteService.countUtenti());
        model.addAttribute("totalAdmin", this.credentialsService.countByRole(Credentials.ADMIN_ROLE));
        model.addAttribute("totalSchede", this.schedaAllenamentoService.countSchede());
        model.addAttribute("totalEsercizi", this.esercizioService.countEsercizi());
        model.addAttribute("totalSchedeIstruttori",
                this.schedaAllenamentoService.countSchedeByRole(Credentials.ADMIN_ROLE));
        model.addAttribute("totalRecensioni", this.recensioneService.countRecensioni());
        return "admin/dashboard";
    }

    @GetMapping("/admin/utenti")
    public String listaUtenti(Model model, @RequestParam(required = false) String keyword) {
        List<Utente> utenti;

        if (keyword != null && !keyword.isEmpty()) {
            utenti = this.utenteService.searchUtenti(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            utenti = this.utenteService.getAllUtenti();
        }

        model.addAttribute("utenti", utenti);
        return "admin/utenti";
    }

    @PostMapping("/admin/ban/{id}")
    public String banUtente(@PathVariable("id") Long idUtente, RedirectAttributes redirectAttributes) {
        Utente utente = this.utenteService.getUtente(idUtente);
        utente.setBanned(true);
        this.utenteService.saveUtente(utente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Utente bannato con successo");
        return "redirect:/admin/utenti";
    }

    @PostMapping("/admin/unban/{id}")
    public String unbanUtente(@PathVariable("id") Long idUtente, RedirectAttributes redirectAttributes) {
        Utente utente = this.utenteService.getUtente(idUtente);
        utente.setBanned(false);
        this.utenteService.saveUtente(utente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Utente sbannato con successo");
        return "redirect:/admin/utenti";
    }

    @PostMapping("/admin/changeAdminRole/{id}")
    public String changeAdminRole(@PathVariable("id") Long idUtente, RedirectAttributes redirectAttributes) {
        Utente utente = this.utenteService.getUtente(idUtente);
        utente.getCredentials().setRole(Credentials.ADMIN_ROLE);
        this.utenteService.saveUtente(utente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Ruolo admin assegnato con successo");
        return "redirect:/admin/utenti";
    }

    @PostMapping("/admin/changeUserRole/{id}")
    public String changeUserRole(@PathVariable("id") Long idUtente, RedirectAttributes redirectAttributes) {
        Utente utente = this.utenteService.getUtente(idUtente);
        utente.getCredentials().setRole(Credentials.DEFAULT_ROLE);
        this.utenteService.saveUtente(utente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Ruolo utente assegnato con successo");
        return "redirect:/admin/utenti";
    }
}
