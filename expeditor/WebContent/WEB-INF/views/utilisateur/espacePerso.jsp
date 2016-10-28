<%@ page import="fr.eni.expeditor.entity.Collaborateur" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expeditor</title>

    <!-- JQuery -->
    <script src="resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>

    <!-- Expeditor scripts -->
    <script src="resources/js/espacePerso.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/espacePerso.css">
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/common.css">

</head>
<body>

    <jsp:include page="/WEB-INF/views/partial/menu.jsp"/>
    <% Collaborateur courant = (Collaborateur)session.getAttribute("collaborateur");  %>

    <div class="flex-container ui raised very padded text container segment">

                <form class="ui equal width form" method="post" action="EspacePersoServlet">
                    <div class="fields">
                        <div class="field">
                            <h1 class="ui center header">Bienvenue sur votre espace personnel</h1>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Nom</label>
                            <input placeholder="Nom" type="text" name="nomCollabo" required="required" value="<%= courant.getNom()%>">
                        </div>
                        <div class="field">
                            <label>Prénom</label>
                            <input placeholder="Prénom" type="text" name="prenomCollabo" required="required" value="<%= courant.getPrenom()%>">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Email</label>
                            <input placeholder="email" type="email" name="emailCollabo" required="required" value="<%= courant.getEmail()%>">
                        </div>
                    </div>
                    <div class="fields">
                        <div class="field">
                            <label>Mot de passe</label>
                            <input placeholder="Mot de passe" type="password" name="mdpCollabo" required="required" value="<%= courant.getMotDePasse()%>">
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

    <%
        String erreur = (String) request.getAttribute("erreur");

        if (erreur != null && erreur.isEmpty()) {
    %>
    <!-- popup d'affichage des erreurs -->
    <div id="popupErreurs" class="ui small modal">
        <div class="header">Erreur de validation</div>

        <div class="content ">
            <p>
                <%=erreur%><br/>
            </p>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(afficherModal('popupErreurs'));
    </script>

    <%
        }
    %>
</body>
</html>
