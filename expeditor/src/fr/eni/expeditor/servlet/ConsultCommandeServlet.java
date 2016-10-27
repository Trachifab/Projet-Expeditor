package fr.eni.expeditor.servlet;

import com.google.gson.JsonElement;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.service.GestionCollaborateurBean;
import fr.eni.expeditor.service.GestionCommandeBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ConsultCommandeServlet")
public class ConsultCommandeServlet extends AbstractServlet {


    @EJB
    private GestionCommandeBean gestionCommandeBean;

    @EJB
    private GestionCollaborateurBean gestionCollaborateurBean;


    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(action){
            case "histo":
                JsonElement jsonStats = gestionCollaborateurBean.recupererStatistiques();
                response.getWriter().write(jsonStats.toString());
                break;

            default:
                break;
        }
    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la liste des commandes en attente ou en cours de traitement
        List<Commande> commandes = gestionCommandeBean.listerCommandesManager();
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/consultCommande.jsp");
        request.setAttribute("commandes", commandes);

        // Récupération du message de résultat d'import du fichier CSV
        String csvResult = null != request.getParameter("csvResult") ? request.getParameter("csvResult").trim() : "";
        request.setAttribute("csvResult", csvResult);

        dispatcher.forward(request, response);

    }
}
