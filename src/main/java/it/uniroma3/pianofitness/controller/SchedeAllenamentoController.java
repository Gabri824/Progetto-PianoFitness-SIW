package it.uniroma3.pianofitness.controller;

import java.util.List;
import java.util.Map;

import it.uniroma3.pianofitness.model.Recensione;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.model.RigaScheda;
import it.uniroma3.pianofitness.model.SchedaAllenamento;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import it.uniroma3.pianofitness.service.EsercizioService;
import it.uniroma3.pianofitness.service.RecensioneService;
import it.uniroma3.pianofitness.service.SchedaAllenamentoService;

@Controller
public class SchedeAllenamentoController {

    @Autowired
    private SchedaAllenamentoService schedaAllenamentoService;

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private EsercizioService esercizioService;

    @Autowired
    private RecensioneService recensioneService;

    @GetMapping("/schede")
    public String listaSchede(Model model, @RequestParam(required = false) String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated()
                && !auth.getPrincipal().equals("anonymousUser");
        boolean isAdmin = isAuthenticated && isAdmin();
        Utente currentUser = isAuthenticated ? getUtenteCorrente() : null;

        List<SchedaAllenamento> schedeAdmin;
        List<SchedaAllenamento> schedePersonali = null;
        List<SchedaAllenamento> allSchede = null;

        if (keyword != null && !keyword.isEmpty()) {
            List<SchedaAllenamento> searchResults = this.schedaAllenamentoService.searchSchede(keyword);
            schedeAdmin = searchResults.stream()
                    .filter(s -> s.getAutore().getCredentials().getRole().equals(Credentials.ADMIN_ROLE))
                    .toList();
            if (isAuthenticated) {
                schedePersonali = searchResults.stream()
                        .filter(s -> s.getAutore().getId().equals(currentUser.getId()))
                        .toList();
            }

            if (isAdmin) {
                allSchede = searchResults;
            }

        } else {
            schedeAdmin = this.schedaAllenamentoService.getAllSchedeByRole(Credentials.ADMIN_ROLE);

            if (isAuthenticated) {
                schedePersonali = this.schedaAllenamentoService.getAllSchedeByAutore(currentUser);
            }

            if (isAdmin) {
                allSchede = this.schedaAllenamentoService.getAllSchede();
            }
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("schedeAdmin", schedeAdmin);
        if (schedePersonali != null) {
            model.addAttribute("schedePersonali", schedePersonali);
        }
        if (allSchede != null) {
            model.addAttribute("allSchede", allSchede);
        }

        return "scheda/elencoSchede";
    }

    @GetMapping("/scheda/{id}")
    public String getScheda(@PathVariable("id") Long schedaId, Model model) {
        SchedaAllenamento scheda = this.schedaAllenamentoService.getScheda(schedaId);
        Map<String, List<RigaScheda>> schedaDivisa = schedaAllenamentoService.getSchedaDivisaPerGiorni(schedaId);
        model.addAttribute("scheda", scheda);
        model.addAttribute("schedaDivisa", schedaDivisa);

        RigaScheda nuovaRiga = new RigaScheda();
        nuovaRiga.setScheda(scheda);
        model.addAttribute("nuovaRiga", nuovaRiga);

        model.addAttribute("listaEsercizi", this.esercizioService.getAllEsercizi());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean canEdit = false;

        // Controlliamo se l'utente è loggato prima di verificare i permessi
        boolean isOwner = false;
        boolean isAdmin = false;

        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

            isAdmin = credentials.getRole().equals(Credentials.ADMIN_ROLE);
            isOwner = scheda.getAutore().getId().equals(credentials.getUtente().getId());

            if (isAdmin || isOwner) {
                canEdit = true;
            }
            model.addAttribute("isAdmin", isAdmin);
        } else {
            model.addAttribute("isAdmin", false);
        }

        // VISIBILITY CHECK
        boolean isSchedaAdmin = scheda.getAutore().getCredentials().getRole().equals(Credentials.ADMIN_ROLE);

        // Guests can only see Admin schemes
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            if (!isSchedaAdmin) {
                model.addAttribute("messaggioErrore", "Non hai i permessi per visualizzare questa scheda privata.");
                return "redirect:/schede"; // Or login
            }
        } else {
            // Registered users can see Admin schemes OR their own schemes
            if (!isSchedaAdmin && !isOwner && !isAdmin) {
                model.addAttribute("messaggioErrore", "Non hai i permessi per visualizzare questa scheda privata.");
                return "redirect:/schede";
            }
        }

        List<Recensione> recensioni = this.recensioneService.getAllRecensioniByScheda(scheda);
        model.addAttribute("recensioni", recensioni);

        model.addAttribute("canEdit", canEdit);
        return "scheda/dettaglioScheda";
    }

    @GetMapping("/schede/formScheda")
    public String showFormScheda(Model model) {
        model.addAttribute("scheda", new SchedaAllenamento());
        return "scheda/formScheda";
    }

    @PostMapping("/schede/formScheda")
    public String creaScheda(@ModelAttribute("scheda") SchedaAllenamento scheda, Model model,
            RedirectAttributes redirectAttributes) {
        if (getUtenteCorrente().isBanned()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non puoi creare schede perché sei bannato");
            return "redirect:/schede";
        }

        Utente autore = getUtenteCorrente();
        scheda.setAutore(autore);
        this.schedaAllenamentoService.saveScheda(scheda);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Scheda creata con successo");
        return "redirect:/scheda/" + scheda.getId();
    }

    @GetMapping("/schede/edit/{id}")
    public String formModificaScheda(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        SchedaAllenamento scheda = schedaAllenamentoService.getScheda(id);
        // Controllo sicurezza: è la mia scheda?
        if (!isMine(scheda) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non puoi modificare la scheda perché non sei l'autore o non sei admin");
            return "redirect:/schede";
        }
        model.addAttribute("scheda", scheda);
        return "/scheda/formModificaScheda";
    }

    @PostMapping("/schede/edit/{id}")
    public String updateScheda(@PathVariable("id") Long id, @ModelAttribute("scheda") SchedaAllenamento schedaForm,
            Model model, RedirectAttributes redirectAttributes) {
        SchedaAllenamento schedaEsistente = schedaAllenamentoService.getScheda(id);
        if (!isMine(schedaEsistente) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore",
                    "Non puoi modificare la scheda perché non sei l'autore o non sei admin");
            return "redirect:/schede";
        }

        // Aggiorniamo solo i campi modificabili
        schedaEsistente.setTitolo(schedaForm.getTitolo());
        schedaEsistente.setDescrizione(schedaForm.getDescrizione());
        schedaEsistente.setDifficolta(schedaForm.getDifficolta());

        schedaAllenamentoService.saveScheda(schedaEsistente);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Scheda modificata con successo");
        return "redirect:/scheda/" + id;
    }

    @GetMapping("/schede/delete/{id}")
    public String deleteScheda(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        SchedaAllenamento scheda = schedaAllenamentoService.getScheda(id);

        if (!isMine(scheda) && !isAdmin()) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "Non hai i permessi per cancellare questa scheda.");
            return "redirect:/schede";
        }

        schedaAllenamentoService.deleteScheda(id);
        redirectAttributes.addFlashAttribute("messaggioSuccesso", "Scheda eliminata con successo!");
        return "redirect:/schede";
    }

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
