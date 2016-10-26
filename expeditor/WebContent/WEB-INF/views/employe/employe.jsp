<%@ page import="fr.eni.expeditor.entity.Commande" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expeditor</title>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>

</head>
<body>

    <jsp:include page="../partial/menu.jsp" />
    <div class="ui one column grid">
        <div class="row">
            <div class="column">

                <div class="ui top attached tabular menu">
                    <div class="active item">Détails de la commande</div>
                </div>
                <div class="ui bottom attached active tab segment">
                    <% Commande commandeATraiter=(Commande)request.getAttribute("commandeATraiter"); %>
                    Identifiant commande : <%=commandeATraiter.getNumero()%>
                    Date de commande : <%=commandeATraiter.getDateCommande()%>
                    Informations client : <%=commandeATraiter.getDateCommande()%>

                </div>

            </div>
        </div>
        <div class="row">

            <div class="column">

                contenu de la ligne 2 (à diviser en deux colonnes)

            </div>

        </div>
    </div>

</body>
</html>
