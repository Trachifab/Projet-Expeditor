package fr.eni.expeditor.servlet;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Role;
import fr.eni.expeditor.service.GestionCollaborateurBean;
import fr.eni.expeditor.service.GestionRoleBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "GestionEmployeServlet")
public class GestionEmployeServlet extends AbstractServlet {

    private List<Collaborateur> collaborateurs;
    private List<Role> roles;

    @EJB
    private GestionCollaborateurBean gestionCollaborateurBean;

    @EJB
    private GestionRoleBean gestionRoleBean;

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(action){
            case "valider":
                ajouter(request);
                init(request, response);
                break;

            case "supprimer":
                supprimer(request);
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
    private void supprimer(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("empId"));
        Collaborateur current = this.gestionCollaborateurBean.rechercherParIdentifiant(id);

        current.setDateArchive(new Date());

        this.gestionCollaborateurBean.enregistrerCollaborateur(current);
    }

    private void ajouter(HttpServletRequest request) {
        Collaborateur collabo = new Collaborateur();

        if(request.getParameter("employeId") != null){
            collabo.setId(Integer.parseInt(request.getParameter("employeId")));
        }

        collabo.setNom(request.getParameter("nomCollabo"));
        collabo.setPrenom(request.getParameter("prenomCollabo"));
        collabo.setEmail(request.getParameter("emailCollabo"));
        collabo.setMotDePasse(request.getParameter("mdpCollabo"));
        collabo.setRole(this.gestionRoleBean.rechercher(request.getParameter("selectRole")));

        //appel a l'EJB
        this.gestionCollaborateurBean.enregistrerCollaborateur(collabo);
    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupération de la liste des collaborateurs
        this.collaborateurs = this.gestionCollaborateurBean.rechercherTous();
        this.roles = this.gestionRoleBean.rechercherTous();
        request.setAttribute("roles", this.roles);
        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/gestionEmploye.jsp");
        request.setAttribute("collaborateurs", this.collaborateurs);

        dispatcher.forward(request, response);
    }
}
