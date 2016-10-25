package fr.eni.expeditor.servlet;

import com.sun.media.jfxmedia.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "EmployeServlet")
public class EmployeServlet extends AbstractServlet {


    private static org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(EmployeServlet.class.getName());

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/employe/employe.jsp");
        dispatcher.forward(request, response);

    }
}
