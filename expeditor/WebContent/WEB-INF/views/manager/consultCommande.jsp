<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.expeditor.entity.Commande" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Commandes</title>

    <!-- jQuery -->
    <script src="resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>

    <!-- Highcharts -->
    <script src="resources/highcharts/highcharts.js"></script>

    <!-- tablesort -->
    <script src="resources/tablesort/tablesort.js"></script>

    <!-- Expeditor scripts -->
    <script src="resources/js/statistiquesEmployes.js"></script>
    <script src="resources/js/consultCommande.js"></script>

    <!-- Expeditor Stylesheets -->
    <link rel="stylesheet" type="text/css"
          href="resources/stylesheets/common.css">
</head>

<body>
<%
    ArrayList<Commande> commandes = (ArrayList<Commande>) request.getAttribute("commandes");
    SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");

    String csvResult = request.getAttribute("csvResult").toString();
    String csvError = request.getAttribute("csvError").toString();
%>
<!-- Inclusion du menu -->
<%@include file="/WEB-INF/views/partial/menu.jsp" %>

<!-- Table des commandes en attente ou en cours de traitement -->
<div class="ui equal width center aligned padded grid">
    <!-- Message de validation de l'import -->
    <% if (!csvResult.isEmpty() || !csvError.isEmpty()) { %>
    <div id="messagesRow" class="row">
        <div class="six wide column"></div>
        <div class="four wide column">
            <!-- Le message de résultat -->
            <% if (!csvResult.isEmpty()) { %>
            <div class="ui positive message">
                <i class="close icon"></i>
                <div class="header">
                    Import de commandes terminés
                </div>
                <p><%= csvResult %>
                </p>
            </div>
            <% } %>

            <!-- Le message d'erreur -->
            <% if (!csvError.isEmpty()) { %>
            <div class="ui error message">
                <i class="close icon"></i>
                <div class="header">
                    Erreur !
                </div>
                <p><%= csvError %>
                </p>
            </div>
            <% } %>
        </div>
        <div class="six wide column"></div>
    </div>
    <% } %>
    <div class="row">
        <div class="two wide column">
        </div>
        <div class="twelve wide column">
            <table class="ui selectable celled sortable table">
                <thead>
                <tr>
                    <th>Numero</th>
                    <th>Client</th>
                    <th>Date de réception</th>
                    <th>Etat</th>
                    <th>Affectation</th>
                </tr>
                </thead>
                <tbody>

                <!-- Parcours de la liste des commandes renvoyées par la servlet -->
                <% for (Commande commande : commandes) { %>

                <!-- On affiche une couleur de ligne différente en fonction de l'état de la commande -->
                <tr class="<% if (commande.getEtat().getCode().equals("ATTE")) { %>  warning <% } else { %> positive <% } %>">
                    <td><%= commande.getNumero() %>
                    </td>
                    <td><%= commande.getClient().getRaisonSociale() %>
                    </td>
                    <td><%= formater.format(commande.getDateCommande()) %>
                    </td>
                    <td>
                        <!-- On affiche une icone différente en fonction de l'état de la commande -->
                        <i class="<% if (commande.getEtat().getCode().equals("ATTE")) { %>  Hourglass Half <% } else { %>  Warning <% } %> icon "></i>
                        <%= commande.getEtat().getLibelle() %>
                    </td>
                    <td><%= commande.getCollaborateur() == null ? "" : commande.getCollaborateur() %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="two wide column">
        </div>
    </div>

    <!-- Histogramme -->
    <div class="row">
        <div class="two wide column">
        </div>
        <div class="twelve wide column">
            <div id="histogramme" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        </div>
        <div class="two wide column">
        </div>
    </div>
</div>
</body>
</html>
