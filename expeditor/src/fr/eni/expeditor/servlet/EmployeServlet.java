package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.entity.Etat;
import fr.eni.expeditor.service.GestionArticleBean;
import fr.eni.expeditor.service.GestionCommandeBean;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "EmployeServlet")
public class EmployeServlet extends AbstractServlet {

	@EJB
	GestionCommandeBean commandeEjb;

	@EJB
	private GestionArticleBean gestionArticleBean;

	private static org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(EmployeServlet.class.getName());

	@Override
	void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		switch(action){
			case "annulerCarton":
				Commande commandeATraiter = new Commande();
				commandeATraiter.setNumero(Integer.parseInt(request.getParameter("idCommande")));
				commandeEjb.libererCommande(commandeATraiter);
				request.getSession().setAttribute("collaborateur", null);
				rediriger("/login.jsp", request, response);
				break;

			default:
				break;
		}

	}

	@Override
	void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession().getAttribute("collaborateur") != null) {
			initialiserPage(request, response);
		} else {
			redirigerVersConnexion(request, response);
		}
	}

	/**
	 * Initialise toutes les données de la page : Commande à traiter Combobox de
	 * selection des articles
	 * 
	 * @param request
	 * @param response
	 */
	private void initialiserPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Collaborateur connectedCollaborateur = (Collaborateur) request.getSession().getAttribute("collaborateur");
		Commande commandeATraiter = commandeEjb.recupererCommandeATraiter(connectedCollaborateur);

		Etat etat = new Etat();
		etat.setCode("ENCO");
		etat.setLibelle("En cours de traitement");

		commandeEjb.modifierEtatCommande(commandeATraiter, etat);
		commandeEjb.affecterCollaborateurACommande(connectedCollaborateur, commandeATraiter);

		chargerListArticleFormatJs(request);

		RequestDispatcher dispatcher = null;
		request.setAttribute("commandeATraiter", commandeATraiter);
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/employe/employe.jsp");
		dispatcher.forward(request, response);
	}

	private void chargerListArticleFormatJs(HttpServletRequest request) {
		List<Article> articles = gestionArticleBean.rechercherTous();

		boolean premierObjet = true;
		StringBuilder tableauArticle = new StringBuilder("articles = [ ");

		for (Article article : articles) {

			if (!premierObjet) {
				tableauArticle.append(",");
			}
			// On génère l'objet article
			tableauArticle.append("{id:\"" + article.getId() + "\", libelle:\""
					+ StringEscapeUtils.escapeEcmaScript(article.getLibelle()) + "\", description:\""
					+ StringEscapeUtils.escapeEcmaScript(article.getDescription()) + "\", poids:\"" + article.getPoids()
					+ "\"}");
			
			premierObjet = false;
		}
		tableauArticle.append("];");

		request.setAttribute("articles", tableauArticle.toString());

	}

	/**
	 * Redirige l'utilisateur vers la page de connexion
	 *
	 * @param request
	 *            Utilisé pour redirigé l'utilisateur vers la page de connexion
	 * @param response
	 *            Obligatoire pour faire le forward
	 * @throws ServletException
	 *             Dans le cas d'une erreur de servlet
	 * @throws IOException
	 *             ???
	 */
	private void redirigerVersConnexion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// L'identifiant n'existe pas, il faut donc afficher un message d'erreur
		// sur le template
		request.setAttribute("error", true);
		// Rediriger l'utilisateur sur la page de login
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
