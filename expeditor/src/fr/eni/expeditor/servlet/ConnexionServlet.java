package fr.eni.expeditor.servlet;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Role;
import fr.eni.expeditor.exception.ConnexionException;
import fr.eni.expeditor.service.GestionCollaborateurBean;
import org.jboss.logging.Logger;

@WebServlet(name = "ConnexionServlet")
public class ConnexionServlet extends AbstractServlet {
    private static Logger LOGGER = Logger.getLogger(ConnexionServlet.class.getName());

    @EJB
    GestionCollaborateurBean gestionCollaborateurBean;

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Passage dans init");
        try {
            connecterUtilisateur(request, response);
        } catch (ConnexionException ce) {
            redirigerVersConnexion(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Connexion de l'utilisateur
     *
     * @param request  Utilisé pour créer la session
     * @param response Obligatoire pour le forward
     * @throws ConnexionException Dans le cas d'une connexion refusée (mauvais login, mauvais mdp)
     */
    private void connecterUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ConnexionException, ServletException, IOException {
        String email = request.getParameter("identifiant");

        // Détection de l'existence de l'utilisateur
        if (!gestionCollaborateurBean.collaborateurExiste(email)) {
            throw new ConnexionException();
        } else {
            // L'utilisateur existe, on vérifie donc son mot de passe
            String motDePasse = request.getParameter("motDePasse");
            Collaborateur collaborateur = gestionCollaborateurBean.rechercherParLoginMotDePasse(email, motDePasse);
            // Maintenant que les infos de connexion sont OK, on peut vérifier son rôle pour la redirection
            Role role = collaborateur.getRole();
            switch (role.getCode()) {
                // Employé
                case "EMPL":
                    request.getSession().setAttribute("utilisateur", collaborateur);
                    rediriger("/employe", request, response);
                    break;
                // Manager
                case "MANA":
                    request.getSession().setAttribute("utilisateur", collaborateur);
                    rediriger("/manager", request, response);
                    break;
                default:
                    LOGGER.error(String.format("Le code ['%s'], role de l'utilisateur %s est érroné.", role, collaborateur.getNom()));
                    throw new ConnexionException();
            }
        }
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
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
