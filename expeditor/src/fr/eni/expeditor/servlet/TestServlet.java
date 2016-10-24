package fr.eni.expeditor.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import fr.eni.expeditor.entity.Utilisateur;
import fr.eni.expeditor.service.GestionUtilisateurBean;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(TestServlet.class.getName());

	@EJB
	private GestionUtilisateurBean gestionUtilisateurBean;

	/**
	 * Default constructor.
	 */
	public TestServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.info("Test hibernate " + gestionUtilisateurBean == null);

		Utilisateur user = new Utilisateur();

		user.setLogin("Toto");
		user.setPassword("test");

		gestionUtilisateurBean.ajouter(user);

		List<Utilisateur> lstUser = gestionUtilisateurBean.getAll();

		LOGGER.info(lstUser.size());

	}

}
