package fr.eni.expeditor.servlet;

import org.jboss.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(TestServlet.class.getName());

	/*
	 * @EJB private GestionUtilisateurBean gestionUtilisateurBean;
	 */

	/**
	 * Default constructor.
	 */
	public TestServlet() {

	}

	@Override
	void action(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = null;

		switch (action) {
		case "action1":
			LOGGER.info("toto1: redirection");

			dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);

			break;
		case "action":
			LOGGER.info("toto2");
			break;
		}

	}

	@Override
	void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * LOGGER.info("Test hibernate " + gestionUtilisateurBean == null);
		 * 
		 * Utilisateur user = new Utilisateur();
		 * 
		 * user.setLogin("Toto"); user.setPassword("test");
		 * 
		 * gestionUtilisateurBean.ajouter(user);
		 * 
		 * List<Utilisateur> lstUser = (List<Utilisateur>)
		 * gestionUtilisateurBean.consulter(Utilisateur.class);
		 * 
		 * LOGGER.info(lstUser.size());
		 * 
		 * // request.setAttribute(arg0, arg1);
		 */

	}

}
