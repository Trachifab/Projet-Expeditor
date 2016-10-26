package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jboss.logging.Logger;

import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.service.GestionArticleBean;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "GestionArticleServlet")
public class GestionArticleServlet extends AbstractServlet {

	@EJB
	private GestionArticleBean gestionArticleBean;

	private static final long serialVersionUID = -4517973155936688634L;

	private static Logger LOGGER = Logger.getLogger(GestionArticleServlet.class.getName());

	@Override
	void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		List<Article> lstArticle = gestionArticleBean.rechercherTous();

		request.setAttribute("lstArticle", lstArticle);

		LOGGER.info("toto");

		dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/gestionArticle.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (action) {
		case "ajouter":

			ajouterArticle(request);
			init(request, response);

			break;

		case "modifier":

			modifierArticle(request);
			init(request, response);

			break;
		}

	}

	private void ajouterArticle(HttpServletRequest request) {

		String libelle = request.getParameter("articleLibelle");
		String description = request.getParameter("articleDescription");
		String poids = request.getParameter("articlePoids");
		Integer poidsInt = null;
		try {
			poidsInt = Integer.parseInt(poids);
		} catch (NumberFormatException ex) {

		}

		Article article = new Article();
		article.setLibelle(libelle);
		article.setDescription(description);
		article.setPoids(poidsInt);

		List<String> erreurs = gestionArticleBean.verifierRG(article);

		if (erreurs == null || erreurs.isEmpty()) {
			gestionArticleBean.enregistrerArticle(article);
		} else {
			request.setAttribute("erreurs", erreurs);
		}

	}

	private void modifierArticle(HttpServletRequest request) {

		String id = request.getParameter("articleId");

		
		
		String libelle = request.getParameter("articleLibelle");
		String description = request.getParameter("articleDescription");
		String poids = request.getParameter("articlePoids");
		Integer poidsInt = null;
		Integer idInt = null;
		try {
			idInt = Integer.parseInt(id);
			poidsInt = Integer.parseInt(poids);
		} catch (NumberFormatException ex) {

		}

		Article article = gestionArticleBean.rechercherParIdentifiant(idInt);
		article.setLibelle(libelle);
		article.setDescription(description);
		article.setPoids(poidsInt);

		List<String> erreurs = gestionArticleBean.verifierRG(article);

		if (erreurs == null || erreurs.isEmpty()) {
			gestionArticleBean.enregistrerArticle(article);
		} else {
			request.setAttribute("erreurs", erreurs);
		}

	}

}
