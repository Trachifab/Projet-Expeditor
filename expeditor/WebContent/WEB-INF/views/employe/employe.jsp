<%@ page import="fr.eni.expeditor.entity.Commande" %>
<%@ page import="fr.eni.expeditor.entity.LigneCommande" %>
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
                    <p>Identifiant commande : <%=commandeATraiter.getNumero()%></p>
                    <p>Date de commande : <%=commandeATraiter.getDateCommande()%></p>
                    <p>Informations client : </p>
                    <ul>
                        <li><%=commandeATraiter.getClient().getRaisonSociale()%></li>
                        <li><%=commandeATraiter.getClient().getAdresse1()%></li>
                        <%if(commandeATraiter.getClient().getAdresse2() != null){%>
                            <li><%=commandeATraiter.getClient().getAdresse2()%></li>
                        <%}%>
                        <%if(commandeATraiter.getClient().getAdresse3() != null){%>
                            <li><%=commandeATraiter.getClient().getAdresse3()%></li>
                        <%}%>
                        <li><%=commandeATraiter.getClient().getCp()%></li>
                        <li><%=commandeATraiter.getClient().getVille()%></li>
                    </ul>
                    <p>Informations commandes : </p>
                    <ul>
                    <% for (LigneCommande ligne : commandeATraiter.getLignesCommande()) { %>
                        <li><%=ligne.getArticle().getLibelle()%> (<%=ligne.getQuantite() %>)</li>
                    <% } %>
                    </ul>
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
