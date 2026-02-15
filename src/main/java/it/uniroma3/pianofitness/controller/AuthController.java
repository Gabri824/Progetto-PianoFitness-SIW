package it.uniroma3.pianofitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.pianofitness.model.Credentials;
import it.uniroma3.pianofitness.model.Utente;
import it.uniroma3.pianofitness.service.CredentialsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private CredentialsService credentialsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String showFormRegister(Model model) {
        model.addAttribute("utente", new Utente());
        model.addAttribute("credentials", new Credentials());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUtente(@ModelAttribute("utente") Utente utente,
            @ModelAttribute("credentials") Credentials credentials, Model model) {
        if (this.credentialsService.existsByEmail(credentials.getEmail())) {
            model.addAttribute("messaggioErrore", "Questa email è già stata utilizzata!");
            return "auth/register";
        }
        credentials.setUtente(utente);
        this.credentialsService.saveCredentials(credentials);
        return "redirect:/login";
    }

    @GetMapping("/profilo/credentials")
    public String showFormModificaCredentials(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

        model.addAttribute("credentials", credentials);
        return "auth/formModificaCredentials";
    }

    @PostMapping("/profilo/credentials")
    public String updateCredentials(
            @RequestParam("oldPassword") String oldPassword,
            @ModelAttribute("credentials") Credentials credentialsForm, Model model,
            RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentialsEsistente = this.credentialsService.getCredentialsByEmail(userDetails.getUsername());

        if (!this.passwordEncoder.matches(oldPassword, credentialsEsistente.getPassword())) {
            redirectAttributes.addFlashAttribute("messaggioErrore", "La vecchia password non è corretta!");
            return "redirect:/profilo/credentials";
        }

        if (credentialsForm != null && !credentialsForm.getPassword().isEmpty()) {
            credentialsEsistente.setPassword(credentialsForm.getPassword());
            this.credentialsService.updateCredentials(credentialsEsistente);
            redirectAttributes.addFlashAttribute("messaggioSuccesso",
                    "Password aggiornata con successo! Effettua nuovamente il login.");
        }

        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("messaggio", "Benvenuto su PianoFitness");
        return "index";
    }
}
