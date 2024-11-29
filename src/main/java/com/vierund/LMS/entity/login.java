package com.vierund.LMS.entity;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cin = request.getParameter("cin");
        String mdp = request.getParameter("mdp");

        Membres utilisateurTrouve = null;
        for (Membres membre : Membres.getMembresList()) {
            if (membre.getCin().equals(cin) && membre.getMdp().equals(mdp)) {
                utilisateurTrouve = membre;
                break;
            }
        }

        if (utilisateurTrouve != null) {
            String statut = utilisateurTrouve.getStatut();

            if ("M".equals(statut)) {
                response.sendRedirect("membreDashboard.html");
            } else if ("A".equals(statut)) {
                response.sendRedirect("adminDashboard.html");
            } else {
                showAlert(response, "Statut inconnu", "login.html");
            }
        } else {
            showAlert(response, "Identifiants incorrects. Veuillez réessayer.", "login.html");
        }
    }

    private void showAlert(HttpServletResponse response, String message, String redirectUrl) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("<html><body>");
        response.getWriter().write("<script type=\"text/javascript\">");
        response.getWriter().write("alert('" + message + "');");
        response.getWriter().write("window.location.href = '" + redirectUrl + "';");
        response.getWriter().write("</script>");
        response.getWriter().write("</body></html>");
    }
}
