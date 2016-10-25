package fr.eni.expeditor.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.service.GestionCollaborateurBean;
import org.jboss.logging.Logger;

@WebServlet(name = "ConnexionServlet")
public class ConnexionServlet extends AbstractServlet {
    private static Logger LOGGER = Logger.getLogger(ConnexionServlet.class.getName());

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Passage dans init");
        String identifiant = request.getParameter("identifiant");

        // Détection de l'existence de l'utilisateur
        GestionCollaborateurBean gestionCollaborateurBean = new GestionCollaborateurBean();
        Collaborateur collaborateur = gestionCollaborateurBean.rechercherParLogin(identifiant);
        if(collaborateur != null){
            LOGGER.info(collaborateur);
        }
        LOGGER.info("L'identifiant n'existe pas sur la base de données");

        // L'identifiant n'existe pas, il faut donc afficher un message d'erreur sur le template
        request.setAttribute("error", true);
        // Rediriger l'utilisateur sur la page de login
        request.getRequestDispatcher("/").forward(request, response);
    }
}
