package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.service.GestionArticleBean;

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

		case "archiver":
			archiverArticle(request);
			init(request, response);

			break;
		}

	}

	/**
	 * Gestion de l'ajout d'article
	 * 
	 * @param request
	 */
	private void ajouterArticle(HttpServletRequest request) {

		Article article = new Article();

        if(!request.getParameter("articleId").isEmpty()){
            int idInt = Integer.parseInt(request.getParameter("articleId"));
            article = gestionArticleBean.rechercherParIdentifiant(idInt);
        }

        article.setLibelle(request.getParameter("articleLibelle"));
        article.setDescription(request.getParameter("articleDescription"));
        article.setPoids(Integer.parseInt(request.getParameter("articlePoids")));

		List<String> erreurs = gestionArticleBean.verifierRG(article);

		if (erreurs == null || erreurs.isEmpty()) {

			try {
				gestionArticleBean.enregistrerArticle(article);
			} catch (Exception ex) {

				erreurs = new ArrayList<String>();
				erreurs.add("Vous ne pouvez pas utilise ce libelle");
				
				request.setAttribute("erreurs", erreurs);
			}
		} else {
			request.setAttribute("erreurs", erreurs);
		}
	}

	private void archiverArticle(HttpServletRequest request) {
		String id = request.getParameter("artId");
		Integer idInt = null;
		try {
			idInt = Integer.parseInt(id);

		} catch (NumberFormatException ex) {

		}

		Article article = gestionArticleBean.rechercherParIdentifiant(idInt);

		article.setDateArchive(new Date());

		gestionArticleBean.enregistrerArticle(article);

	}
}
