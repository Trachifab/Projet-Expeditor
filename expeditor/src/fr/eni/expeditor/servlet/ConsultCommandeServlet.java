package fr.eni.expeditor.servlet;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.entity.Role;
import fr.eni.expeditor.service.GestionCommandeBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "ConsultCommandeServlet")
public class ConsultCommandeServlet extends AbstractServlet {


    @EJB
    private GestionCommandeBean gestionCommandeBean;


    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la liste des commandes en attente ou en cours de traitement
        List<Commande> commandes = gestionCommandeBean.listerCommandesManager();
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/consultCommande.jsp");
        request.setAttribute("commandes", commandes);

        dispatcher.forward(request, response);

    }
}
