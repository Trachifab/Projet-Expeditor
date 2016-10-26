package fr.eni.expeditor.servlet;

import fr.eni.expeditor.entity.Collaborateur;
import fr.eni.expeditor.entity.Role;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Administrateur on 25/10/2016.
 */
@WebServlet(name = "ConsultCommandeServlet")
public class ConsultCommandeServlet extends AbstractServlet {

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Collaborateur employeCourant = new Collaborateur();
        employeCourant.setEmail("emp@bouchon.com");
        employeCourant.setNom("BERNARD");
        employeCourant.setPrenom("Jean");

        Role roleEmploye = new Role();
        roleEmploye.setCode("EMPL");
        roleEmploye.setLibelle("Employé");
        employeCourant.setRole(roleEmploye);

        Collaborateur managerCourant = new Collaborateur();
        managerCourant.setEmail("man@bouchon.com");
        managerCourant.setNom("DUPONT");
        managerCourant.setPrenom("Thierry");

        Role roleManager = new Role();
        roleManager.setCode("MANA");
        roleManager.setLibelle("Manager");

        managerCourant.setRole(roleManager);

        session.setAttribute("collaborateur",employeCourant);
//        session.setAttribute("collaborateur", managerCourant);

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/views/manager/consultCommande.jsp");
        dispatcher.forward(request, response);


    }
}
