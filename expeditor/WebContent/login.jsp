<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion | Expeditor</title>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.css">
    <script src="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/stylesheets/connexion.css">
    <!-- Expeditor scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/connexion.js"></script>
</head>
<body>
<!-- Le conteneur, centré en milieu de page -->
<div id="main-container" class="ui raised very padded text container segment">

    <p class="centered">Vous devez vous connecter pour accéder à l'espace expedition</p>

    <!-- Formulaire de connexion -->
    <form id="login-form" class="ui form" method="POST" action="${pageContext.request.contextPath}/connexion">

        <div id="login-inputs">
            <!-- Identifiant -->
            <div class="field">
                <label>
                    Identifiant<input type="text" name="identifiant">
                </label>
            </div>

            <!-- Mot de passe -->
            <div class="field">
                <label>
                    Mot de passe
                    <input type="password" name="motDePasse">
                </label>
            </div>

            <!-- Mot de passe oublié -->
            <div id="mdpOublieContainer">
                <p id="mdpOublie" class="right floated">Mot de passe oublié ?</p>
            </div>
        </div>

        <!-- Message de validation -->
        <div class="ui success message">
            <i class="close icon"></i>
            <p>Un email à été envoyé à l'adresse indiquée.</p>
        </div>

        <!-- Message d'erreur -->
        <% if ((request.getAttribute("error") != null) && ((Boolean) request.getAttribute("error"))) { %>
        <div id="message-erreur" class="ui error message">
            <i class="close icon"></i>
            <div class="header">
                Il y a eu une erreur lors de la tentative de connexion
            </div>
            <ul class="list">
                <li>L'identifiant n'existe peut-être pas.</li>
                <li>Le mot de passe est peut-être erroné.</li>
                <li>Si vous avez oublié votre mot de passe, cliquez sur le lien ci-dessus.</li>
            </ul>
        </div>
        <%}%>

        <!-- Bouton de connexion -->
        <div class="connexion">
            <button class="ui green center floated animated button" type="submit">
                <div class="visible content">Connexion</div>
                <div class="hidden content">
                    <i class="sign in icon"></i>
                </div>
            </button>
        </div>

    </form>
</div>

<%@include file="/WEB-INF/views/partial/motDePasseOublie.jsp" %>

</body>
</html>
