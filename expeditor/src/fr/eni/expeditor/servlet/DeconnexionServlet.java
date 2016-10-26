package fr.eni.expeditor.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeconnexionServlet")
public class DeconnexionServlet extends AbstractServlet {

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Suppression de la session courante de l'utilisateur
        request.getSession().setAttribute("collaborateur", null);
        // Redirection vers la page de login
        rediriger("/login.jsp", request, response);
    }
}
