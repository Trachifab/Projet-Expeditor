package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        
        
        List<Article> lstArticle = gestionArticleBean.rechercherTous();
        
        request.setAttribute("lstArticle", lstArticle);
        
        LOGGER.info("toto");
        
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/gestionArticle.jsp");
        dispatcher.forward(request, response);
    }
}
