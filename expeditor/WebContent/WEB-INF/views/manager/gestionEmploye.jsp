<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.eni.expeditor.entity.Role" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Employés</title>

    <!-- JQuery -->
    <script src="resources/JQuery/jquery-3.1.1.min.js"></script>

    <!-- SemanticUI -->
    <link rel="stylesheet" type="text/css" href="resources/semanticUI/semantic.min.css">
    <script src="resources/semanticUI/semantic.min.js"></script>

    <!-- tablesort -->
    <script src="resources/tablesort/tablesort.js"></script>

    <!-- Expeditor scripts -->
    <script src="resources/js/gestionEmploye.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/gestionEmploye.css">
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/common.css">
</head>

<body>
<%
    ArrayList<Collaborateur> collaborateurs = (ArrayList<Collaborateur>) request.getAttribute("collaborateurs");
    SimpleDateFormat formater = new SimpleDateFormat("dd MMMM yyyy 'à' hh:mm:ss");
%>
<!-- Inclusion du menu -->
<%@include file="/WEB-INF/views/partial/menu.jsp" %>
<% List<Role> roles = (List<Role>) request.getAttribute("roles"); %>

    <!-- Table des collaborateurs -->
    <div class="ui equal width center aligned padded grid">
        <div class="row">
            <div class="two wide column">
            </div>
            <div class="twelve wide column">
                <table class="ui selectable celled sortable table">
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
                            <div class="field">
                                <button class="ui icon brown small button"
                                        onclick="afficherEmployeModale('<%=collaborateur.getId()%>',
                                                '<%=collaborateur.getNom()%>', '<%=collaborateur.getPrenom()%>',
                                                '<%=collaborateur.getEmail()%>', '<%=collaborateur.getMotDePasse()%>','<%=collaborateur.getRole().getCode()%>')">
                                    <i class="small edit icon"></i>
                                </button>
                                <button class="ui icon red small button" onclick="afficherSuppModale('supprimerModale', '<%=collaborateur.getId()%>')">
                                    <i class="small trash icon"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <div class="two wide column">
            </div>
        </div>
        <button class="ui green center aligned animated button" onclick="afficherEmployeModale('', '', '', '', '', '')">
            <div class="visible content">Ajouter</div>
            <div class="hidden content">
                <i class="add icon"></i>
            </div>
        </button>
    </div>

<!-- popup d'ajout et de modifications des employés -->
<div id="modaleEmploye" class="ui small modal">
        <form class="ui equal width form" method="post" action="GestionEmployeServlet">
            <input type="hidden" name="employeId" id="employeId" />
            <div class="fields">
                <div class="field header">
                    <h1 class="ui center header">Ajouter un employé</h1>
                </div>
            </div>
            <div class="fields content">
                <div class="field">
                    <label>Nom</label>
                    <input placeholder="Nom" type="text" maxlength="50" name="nomCollabo" required="required" >
                </div>
                <div class="field margin">
                    <label>Prénom</label>
                    <input placeholder="Prénom" type="text" maxlength="50" name="prenomCollabo" required="required" >
                </div>
            </div>
            <div class="fields">
                <div class="field">
                    <label>Email</label>
                    <input placeholder="Email" type="email" maxlength="100" name="emailCollabo"  required="required">
                </div>
            </div>
            <div class="fields">
                <div class="field">
                    <label>Mot de passe</label>
                    <input placeholder="Mot de passe" maxlength="50" type="password" name="mdpCollabo"  required="required" >
                </div>
            </div>
            <div class="fields">
                <div class="field">
                    <label>Rôle</label>
                    <select name="selectRole" class="ui dropdown" id="select">
                        <% for (Role item: roles) {%>
                            <option value="<%=item.getCode()%>"><%=item.getLibelle()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
            <div class="fields">
                <div class="field">
                    <button class="ui red left floated animated button" onclick="fermerModale('modaleEmploye')">
                        <div class="visible content">Annuler</div>
                        <div class="hidden content">
                            <i class="remove icon"></i>
                        </div>
                    </button>
                    <button class="ui green right floated animated button" type="submit" name="action" value="valider">
                        <div class="visible content">Valider</div>
                        <div class="hidden content">
                            <i class="checkmark icon"></i>
                        </div>
                    </button>
                </div>
            </div>
        </form>
</div>

<!-- modale de suppression -->
    <div class="ui basic modal" id="supprimerModale">
        <i class="close icon"></i>
        <div class="header">
            <div class=" flex-container field">
                    <i class="huge trash icon"></i>
            </div>
            <div class="flex-container field">
                Archiver un employé
            </div>
        </div>
        <div class=" image content">
            <div class="flex-container description">
                <p class="flex-container">Voulez-vous vraiment archiver cet employé ?</p>
            </div>
        </div>
        <div>
            <form class="form" id="suppForm" method="post" action="GestionEmployeServlet">
                <input type="hidden" name="empId" id="empId">
                <button class="ui left floated red basic inverted button" onclick="fermerModale('supprimerModale')" >
                    <i class="remove icon"></i>
                    Non
                </button>
                <button class="ui right floated green basic inverted button" type="submit" name="action" value="supprimer">
                    <i class="checkmark icon"></i>
                    Oui
                </button>
            </form>
        </div>
    </div>
</body>
</html>
