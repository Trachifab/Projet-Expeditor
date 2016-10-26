<%@ page import="fr.eni.expeditor.entity.Collaborateur" %>
<!-- Faut pas faire ça, mais fait chier de le mettre dans tous les templates sinon -->
<script src="${pageContext.request.contextPath}/resources/js/menu.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui attached stackable menu">
    <div class="ui container">
        <div class=" ui simple dropdown item">
            <i class="content icon"></i>
            <i class="dropdown icon"></i>
            <div class="menu">
                <% if (((Collaborateur) session.getAttribute("collaborateur")).getRole().getCode().equals("MANA")) { %>
                <a href="ConsultCommandeServlet" class="item"><i class="dashboard icon"></i>Tableau de bord</a>
                <a href="GestionEmployeServlet" class="item"><i class="cubes icon"></i>Employés</a>
                <a href="GestionArticleServlet" class="item"><i class="users icon"></i>Articles</a>
                <a class="item"><i class="file icon"></i>Importer un CSV</a>
                <% } else {%>
                <a href="EmployeServlet" class="item"><i class="dropbox icon"></i>Commande</a>
                <% } %>
            </div>
        </div>
        <a href="EspacePersoServlet" class="right item">
            <%= session.getAttribute("collaborateur")  %>
            <i class="user icon"></i>
        </a>
        <a class="item">
            <i class="red sign out icon"></i>
        </a>
        <form id="deconnexionForm" style="display: none;" action="${pageContext.request.contextPath}/deconnexion" method="POST">
            <button class="ui transparent button deconnexion" type="submit"></button>
        </form>
    </div>
</div>