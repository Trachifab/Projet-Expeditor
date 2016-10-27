package fr.eni.expeditor.servlet;

import fr.eni.expeditor.service.LectureFichierCSVBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by d1503betournej on 26/10/2016.
 */
@WebServlet(name = "ImportCSVServlet")
public class ImportCSVServlet extends AbstractServlet {


    @EJB
    private LectureFichierCSVBean lectureFichierCSVBean;

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lectureFichierCSVBean.lectureFichierCommandes(System.getProperty("user.dir") + "/download/commandes.csv");
    }

}
