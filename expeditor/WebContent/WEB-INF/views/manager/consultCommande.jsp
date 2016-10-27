<%--
  Created by IntelliJ IDEA.
  User: Administrateur
  Date: 25/10/2016
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.expeditor.entity.Commande" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Commandes</title>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>
</head>

<body>
<%
    ArrayList<Commande> commandes = (ArrayList<Commande>) request.getAttribute("commandes");
    SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
%>
<!-- Inclusion du menu -->
<%@include file="/WEB-INF/views/partial/menu.jsp" %>

<!-- Table des commandes en attente ou en cours de traitement -->
<div class="ui equal width center aligned padded grid">
    <div class="row">
        <div class="two wide column">
        </div>
        <div class="twelve wide column">
            <table class="ui selectable celled table">
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
</div>


</body>
</html>
