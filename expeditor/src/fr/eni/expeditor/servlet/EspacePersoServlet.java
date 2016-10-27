package fr.eni.expeditor.servlet;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.service.GestionCollaborateurBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "EspacePersoServlet")
public class EspacePersoServlet extends AbstractServlet {

    private Collaborateur employeCourant;
    private HttpSession session;

    @EJB
    private GestionCollaborateurBean gestionCollaborateurBean;

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (action){
            case "modifier":
                modifier(request);
                init(request, response);
                break;
            case "annuler":
                init(request, response);
                break;

            default:
                break;
        }
    }

    /**
     *
     * @param request
     */
    private void modifier(HttpServletRequest request) {
        employeCourant.setNom(request.getParameter("nomCollabo"));
        employeCourant.setPrenom(request.getParameter("prenomCollabo"));
        employeCourant.setEmail(request.getParameter("emailCollabo"));
        employeCourant.setMotDePasse(request.getParameter("mdpCollabo"));

        //appel a l'EJB
        gestionCollaborateurBean.enregistrerCollaborateur(employeCourant);

        //set du nouveau collaborateur
        session.setAttribute("collaborateur",employeCourant);
    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        employeCourant = (Collaborateur)session.getAttribute("collaborateur");

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/utilisateur/espacePerso.jsp");
        dispatcher.forward(request, response);
    }
}
