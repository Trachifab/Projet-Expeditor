package fr.eni.expeditor.servlet;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.entity.Etat;
import fr.eni.expeditor.service.GestionCommandeBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "EmployeServlet")
public class EmployeServlet extends AbstractServlet {

    @EJB
    GestionCommandeBean commandeEjb;

    private static org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(EmployeServlet.class.getName());

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("collaborateur") != null){
            initialiserPage(request, response);
        } else {
            redirigerVersConnexion(request, response);
        }
    }

    /**
     * Initialise toutes les données de la page :
     *      Commande à traiter
     *      Combobox de selection des articles
     * @param request
     * @param response
     */
    private void initialiserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Collaborateur connectedCollaborateur = (Collaborateur) request.getSession().getAttribute("collaborateur");
        Commande commandeATraiter = commandeEjb.recupererCommandeATraiter(connectedCollaborateur);

        Etat etat = new Etat();
        etat.setCode("ENCO");
        etat.setLibelle("En cours de traitement");

        commandeEjb.modifierEtatCommande(commandeATraiter, etat);
        commandeEjb.affecterCollaborateurACommande(connectedCollaborateur, commandeATraiter);

        RequestDispatcher dispatcher = null;
        request.setAttribute("commandeATraiter", commandeATraiter);
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/employe/employe.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Redirige l'utilisateur vers la page de connexion
     *
     * @param request  Utilisé pour redirigé l'utilisateur vers la page de connexion
     * @param response Obligatoire pour faire le forward
     * @throws ServletException Dans le cas d'une erreur de servlet
     * @throws IOException      ???
     */
    private void redirigerVersConnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // L'identifiant n'existe pas, il faut donc afficher un message d'erreur sur le template
        request.setAttribute("error", true);
        // Rediriger l'utilisateur sur la page de login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
