package com.vierund.LMS.controller;

import com.vierund.LMS.Membres;
import com.vierund.LMS.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class loginController {

    @Autowired
    private loginService LogService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("cin") String cin,
                        @RequestParam("mdp") String mdp, Model model) {
        Membres utilisateur = LogService.authenticate(cin, mdp);

        if (utilisateur != null) {
            if ("M".equals(utilisateur.getStatut())) {
                return "membreDashboard";
            } else if ("A".equals(utilisateur.getStatut())) {
                return "adminDashboard";
            } else {
                model.addAttribute("error", "Statut inconnu");
                return "login";
            }
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "login";
        }
    }
}
