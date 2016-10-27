package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.expeditor.entity.Commande;
import fr.eni.expeditor.service.GestionCommandeBean;

/**
 * Servlet implementation class BonLivraisonServlet
 */
public class BonLivraisonServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionCommandeBean gestionCommandeBean;
	
	@Override
	void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer idCommande = 101;
		
		Commande commande = gestionCommandeBean.rechercherParNumero(idCommande);
		
		request.setAttribute("commande", commande);
		
		String poidsTotal = "5.505";
		request.setAttribute("poidsTotal", poidsTotal);
		
		List<String> lstLignes = new ArrayList<String>();
		lstLignes.add("Article 1 : 5 unités");
		lstLignes.add("Article 2 : 5 unités");
		lstLignes.add("Article 3 : 5 unités");
		lstLignes.add("Article 4 : 5 unités");
		
		request.setAttribute("lstLignes", lstLignes);
		
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/bonLivraison.jsp");
		dispatcher.forward(request, response);

	}

}
