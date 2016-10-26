<%@ page import="fr.eni.expeditor.entity.Collaborateur" %><%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 25/10/2016
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expeditor</title>

    <!-- JQuery -->
    <script src="${pageContext.request.contextPath}/resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.css">
    <script src="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.js"></script>

    <!-- Expeditor scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/espacePerso.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/espacePerso.css">

</head>
<body>

    <jsp:include page="../partial/menu.jsp"/>
    <% Collaborateur courant = (Collaborateur)session.getAttribute("collaborateur");  %>

    <div class="flex-container">

                <form class="ui equal width form" method="post" action="EspacePersoServlet">
                    <div class="fields">
                        <div class="field">
                            <h1 class="ui center header">Bienvenue sur votre espace personnel</h1>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Nom</label>
                            <input placeholder="Nom" type="text" name="nomCollabo" value="<%= courant.getNom()%>">
                        </div>
                        <div class="field">
                            <label>Prénom</label>
                            <input placeholder="Prénom" type="text" name="prenomCollabo" value="<%= courant.getPrenom()%>">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Email</label>
                            <input placeholder="email" type="email" name="emailCollabo" value="<%= courant.getEmail()%>">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Mot de passe</label>
                            <input placeholder="Mot de passe" type="password" name="mdpCollabo" value="<%= courant.getMotDePasse()%>">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <button class="ui red left floated animated button" type="submit" name="action" value="annuler">
                                <div class="visible content">Annuler</div>
                                <div class="hidden content">
                                    <i class="remove icon"></i>
                                </div>
                            </button>
                            <button class="ui green right floated animated button" type="submit" name="action" value="modifier">
                                <div class="visible content">Modifier</div>
                                <div class="hidden content">
                                    <i class="checkmark icon"></i>
                                </div>
                            </button>
                        </div>

                    </div>
                </form>

            </div>
</body>
</html>
