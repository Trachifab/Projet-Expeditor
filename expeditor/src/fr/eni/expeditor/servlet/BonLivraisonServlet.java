package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.service.GestionArticleBean;
import fr.eni.expeditor.service.GestionCommandeBean;

/**
 * Servlet implementation class BonLivraisonServlet
 */
public class BonLivraisonServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionCommandeBean gestionCommandeBean;

	@EJB
	private GestionArticleBean gestionArticleBean;
	
	@Override
	void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String strIdCommande = request.getParameter("idCommande");

		Integer idCommande = Integer.parseInt(strIdCommande);

		Commande commande = gestionCommandeBean.rechercherParNumero(idCommande);

		request.setAttribute("commande", commande);

		String poidsTotal = request.getParameter("poidsTotal");
		request.setAttribute("poidsTotal", poidsTotal);

		String donnee = request.getParameter("donnee");


		List<String> lstLignes = traiterDonnee(donnee);

		request.setAttribute("lstLignes", lstLignes);

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/bonLivraison.jsp");
		dispatcher.forward(request, response);

	}

	private List<String> traiterDonnee(String donnee) {
		
		List<String> lstLignes = new ArrayList<String>();
		
		donnee.replace("[", "");
		donnee.replace("]", "");
		
		Pattern p2 = Pattern.compile("\"idArticle\":\"([0-9]+)\",\"nb\":\"([0-9]+)\"");
		
		Pattern p = Pattern.compile("\\{([^\\{^\\}]+)\\}");
		Matcher m = p.matcher(donnee);
		while (m.find()) {
			
			String temp = m.group();
			
			Matcher m2 = p2.matcher(temp);
			m2.find();
			
			String idArticle = m2.group(1);
			String nbArticle = m2.group(2);
			
			Article article = gestionArticleBean.rechercherParIdentifiant(Integer.parseInt(idArticle));
			//Logger.getLogger("test").info(m2.group(1)+" - "+m2.group(2));
			
			lstLignes.add(article.getLibelle()+" : "+nbArticle+" unités");
			
		}
		
		return lstLignes;
	}

}

