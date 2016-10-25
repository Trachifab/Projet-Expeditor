<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion | Expeditor</title>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="stylesheets/connexion.css">
</head>
<body>

<!-- Le conteneur, centré en milieu de page -->
<div id="main-container" class="ui raised very padded text container segment">

    <p class="centered">Vous devez vous connecter pour accéder à l'espace expedition</p>

    <!-- Formulaire de connexion -->
    <form id="login-form" class="ui form centered small">
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
        <button id="connection-button" class="ui button centered" type="submit">Connexion</button>
    </form>
</div>

</body>
</html>
