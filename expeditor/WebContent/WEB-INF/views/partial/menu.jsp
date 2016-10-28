<%@ page import="fr.eni.expeditor.entity.Collaborateur" %>
<!-- menu stylesheets -->
<link rel="stylesheet" type="text/css" href="resources/stylesheets/menu.css">
<!-- menu scripts -->
<script src="resources/js/menu.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="expeditorMenu" class="ui attached stackable menu">
    <div class="ui container">
        <div class="ui dropdown item">
            <i class="content icon white"></i>
            <i class="dropdown icon white"></i>
            <div class="menu">
                <% if (((Collaborateur) session.getAttribute("collaborateur")).getRole().getCode().equals("MANA")) { %>
                <a href="ConsultCommandeServlet" class="item"><i class="dashboard icon white"></i>Tableau de bord</a>
                <a href="GestionEmployeServlet" class="item"><i class="users icon white"></i>Employés</a>
                <a href="GestionArticleServlet" class="item"><i class="cubes icon white"></i>Articles</a>
                <a class="item btnImportCsv"><i class="file icon"></i>Importer un CSV/XLS</a>
                <% } else {%>
                <a href="${pageContext.request.contextPath}/employe" class="item"><i
                        class="dropbox icon"></i>Commande</a>
                <% } %>
            </div>
        </div>
        <a href="EspacePersoServlet" class="right item">
            <%= session.getAttribute("collaborateur")  %>
            <i class="user icon"></i>
        </a>
        <a id="signout" class="item">
            <i class="white sign out icon"></i>
        </a>
        <form id="deconnexionForm" style="display: none;" action="${pageContext.request.contextPath}/deconnexion"
              method="POST">
            <button class="ui transparent button deconnexion" type="submit"></button>
        </form>
    </div>
</div>

<!-- The import CSV modal -->
<%@include file="/WEB-INF/views/partial/modaleImportCSV.jsp" %>