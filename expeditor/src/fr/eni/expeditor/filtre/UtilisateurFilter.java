package fr.eni.expeditor.filtre;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Role;
import org.jboss.logging.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UtilisateurFilter")
public class UtilisateurFilter implements Filter {
    private static Logger LOGGER = Logger.getLogger(UtilisateurFilter.class.getName());

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // Récupération de la session
        HttpServletRequest request = (HttpServletRequest) req;
        Collaborateur collaborateur = (Collaborateur) request.getSession().getAttribute("collaborateur");
        HttpServletResponse response = (HttpServletResponse) resp;
        String cheminActuel = request.getServletPath();

        // Si le chemin demandé est une ressource, on ne filtre rien
        if (cheminActuel.contains("/resources/")) {
            chain.doFilter(request, response);
        }

        // Tentative d'accès à la page de login
        if ("/".equals(cheminActuel) || "/login.jsp".equals(cheminActuel)) {
            //// Verification de l'existence de la session
            ////// Si session -> redirect vers la page correspondante
            if (collaborateur != null) {
                RedirigerVersAccueilCollaborateur(collaborateur, response, request);
            }
            ////// Sinon -> ok
            else {
                chain.doFilter(req, resp);
            }
        }
        // Tentative d'accès à une autre page
        else {
            //// Verification de l'existence de la session
            ////// Si session ->
            if (collaborateur != null) {
                Role role = collaborateur.getRole();

                switch (cheminActuel) {
                    case "/employe":
                        //////// Si pas autorisé -> redirection vers sa page d'accueil
                        if ("MANA".equals(role.getCode())) {
                            response.sendRedirect(request.getContextPath() + "/manager");
                        }
                        //////// Sinon -> ok
                        else {
                            chain.doFilter(req, resp);
                        }
                        break;
                    case "/manager":
                    case "/GestionEmployeServlet":
                    case "/GestionArticleServlet":
                        //////// Si pas autorisé -> redirection vers sa page d'accueil
                        if ("EMPL".equals(role.getCode())) {
                            response.sendRedirect(request.getContextPath() + "/employe");

                        }
                        //////// Sinon -> ok
                        else {
                            chain.doFilter(req, resp);
                        }
                        break;
                    default:
                        chain.doFilter(req, resp);
                        break;
                }
            } else {
                if (!"/connexion".equals(cheminActuel)) {
                    ////// Sinon -> redirection vers la page de login
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                } else {
                    chain.doFilter(req, resp);
                }
            }
        }
    }

    private void RedirigerVersAccueilCollaborateur(Collaborateur collaborateur, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Role role = collaborateur.getRole();
        switch (role.getCode()) {
            // Employé
            case "EMPL":
                response.sendRedirect(request.getContextPath() + "/employe");
                break;
            // Manager
            case "MANA":
                response.sendRedirect(request.getContextPath() + "/manager");
                break;
            default:
                LOGGER.error(String.format("Le code ['%s'], role de l'utilisateur %s est érroné.", role, collaborateur.getNom()));
                break;
        }
    }
}