<%@ page import="fr.eni.expeditor.entity.Collaborateur" %>
<!-- menu stylesheets -->
<link rel="stylesheet" type="text/css" href="resources/stylesheets/menu.css">
<!-- menu scripts -->
<script src="resources/js/menu.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="expeditorMenu" class="ui attached stackable menu">
    <div class="ui container">
        <div class="ui simple dropdown item">
            <i class="content icon white"></i>
            <i class="dropdown icon white"></i>
            <div class="menu">
                <% if (((Collaborateur) session.getAttribute("collaborateur")).getRole().getCode().equals("MANA")) { %>
                <a href="ConsultCommandeServlet" class="item"><i class="dashboard icon white"></i>Tableau de bord</a>
                <a href="GestionEmployeServlet" class="item"><i class="cubes icon white"></i>Employés</a>
                <a href="GestionArticleServlet" class="item"><i class="users icon white"></i>Articles</a>
                <a class="item btnImportCsv"><i class="file icon"></i>Importer un CSV</a>
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
        <a class="item">
            <i class="red sign out icon"></i>
        </a>
        <form id="deconnexionForm" style="display: none;" action="${pageContext.request.contextPath}/deconnexion"
              method="POST">
            <button class="ui transparent button deconnexion" type="submit"></button>
        </form>
    </div>
</div>

<!-- The import CSV modal -->
<div class="ui modal importCsv">
    <div class="header">Importer un fichier CSV</div>
    <div class="content">
        <form id="formImportCsv" action="ImportCSVServlet" enctype="multipart/form-data" method="POST">
            <p>Indiquez ci-dessous le chemin vers le fichier CSV à importer, afin d'ajouter les nouvelles commandes à l'application.</p>
            <div>
                <div class="field">
                    <div class="ui action input">
                        <input type="text" id="_attachmentName">
                        <label for="attachmentName" class="ui icon button btn-file">
                            <i class="attachment basic icon search"></i>
                            <input type="file" id="attachmentName" name="attachmentName" style="display: none">
                        </label>
                    </div>
                </div>
            </div>
            <div class="button-container">
                <button class="ui primary button" type="submit" name="action" value="import">Importer</button>
            </div>
        </form>
    </div>
</div>