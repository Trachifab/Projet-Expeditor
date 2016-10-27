<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.expeditor.entity.Commande" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="fr.eni.expeditor.entity.LigneCommande" %>
<html>
<head>
    <title>Employés</title>

    <script src="${pageContext.request.contextPath}/resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- Semantic UI -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.css">
    <script src="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/highcharts.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/statistiquesEmployes.js"></script>

</head>

<body>
<%
    ArrayList<Collaborateur> collaborateurs = (ArrayList<Collaborateur>) request.getAttribute("collaborateurs");
    SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
%>
<!-- Inclusion du menu -->
<%@include file="/WEB-INF/views/partial/menu.jsp" %>

<!-- Table des collaborateurs -->
<div class="ui equal width center aligned padded grid">
    <div class="row">
        <div class="two wide column">
        </div>
        <div class="twelve wide column">
            <table class="ui selectable celled table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Role</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>

                <!-- Parcours de la liste des collaborateurs renvoyés par la servlet -->
                <% for (Collaborateur collaborateur : collaborateurs) { %>

                <!-- On affiche une couleur de ligne différente en fonction du role du collaborateur -->
                <tr class="<% if (collaborateur.getRole().getCode().equals("MANA")) { %>  positive  <% } else { %> warning <% } %>">
                    <td><%= collaborateur.getId() %>
                    </td>
                    <td>
                        <i class="<% if (collaborateur.getRole().getCode().equals("MANA")) { %>  spy  <% } else { %> user <% } %> icon "></i>
                        <%= collaborateur.getRole().getLibelle() %>
                    </td>
                    <td><%= collaborateur.getNom() %>
                    </td>
                    <td><%= collaborateur.getPrenom() %>
                    </td>
                    <td><%= collaborateur.getEmail() %>
                    </td>
                    <td>
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

<div id="histogramme" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script>
    employes : [];
</script>

    <% for(Collaborateur col : collaborateurs) {%>
        <% if(col.getRole().getCode().equals("EMPLOYE")){ %>
            employes.put('<%=col.getPrenom() + " " + col.getNom()%>');
        <% } %>
    <% } %>

<script>
    afficherHisto(employes, statistiques);
</script>

</body>
</html>
