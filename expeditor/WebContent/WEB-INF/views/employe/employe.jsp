<%@ page import="fr.eni.expeditor.entity.Commande"%>
<%@ page import="fr.eni.expeditor.entity.LigneCommande"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Expeditor</title>

<script
	src="resources/JQuery/jquery-3.1.1.min.js"></script>

<!-- Semantic UI -->
<link rel="stylesheet" type="text/css"
	href="resources/semanticUI/semantic.min.css">
<script
	src="resources/semanticUI/semantic.min.js"></script>


<!-- Expeditor stylesheets -->
<link rel="stylesheet" type="text/css" href="resources/stylesheets/common.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/employe.css">



<!-- Récupération des articles dans un tableau js appellé articles -->
<script type="text/javascript">
	
<%=request.getAttribute("articles")%>
	
</script>

<script type="text/javascript" src="resources/js/employe.js"></script>

</head>
<body>

	<jsp:include page="/WEB-INF/views/partial/menu.jsp" />
	<div class="ui equal width aligned padded grid">
		<div class="row">
			<div class="two wide column"></div>
			<div class="twelve wide column">

				<div class="ui top attached tabular menu">
					<div class="active item">Détails de la commande</div>
				</div>
				<div class="ui bottom attached active tab segment">

					<%
						Commande commandeATraiter = (Commande) request.getAttribute("commandeATraiter");
					%>
					<%
						if (commandeATraiter != null) {
					%>
					<p>
						Identifiant commande :
						<%=commandeATraiter.getNumero()%></p>
					<p>
						Date de commande :
						<%=commandeATraiter.getDateCommande()%></p>
					<p>Informations client :</p>
					<ul>
						<li><%=commandeATraiter.getClient().getRaisonSociale()%></li>
						<li><%=commandeATraiter.getClient().getAdresse1()%></li>
						<%
							if (commandeATraiter.getClient().getAdresse2() != null) {
						%>
						<li><%=commandeATraiter.getClient().getAdresse2()%></li>
						<%
							}
						%>
						<%
							if (commandeATraiter.getClient().getAdresse3() != null) {
						%>
						<li><%=commandeATraiter.getClient().getAdresse3()%></li>
						<%
							}
						%>
						<li><%=commandeATraiter.getClient().getCp()%></li>
						<li><%=commandeATraiter.getClient().getVille()%></li>
					</ul>
					<p>Informations commandes :</p>
					<ul>
						<%
							for (LigneCommande ligne : commandeATraiter.getLignesCommande()) {
						%>
						<li><%=ligne.getArticle().getLibelle()%> (<%=ligne.getQuantite()%>)</li>
						<%
							}
						%>
					</ul>
					<%
						} else {
					%>
					<p>Aucune commande à traiter.</p>
					<%
						}
					%>
				</div>

			</div>
			<div class="two wide column"></div>
		</div>
		<div class="row">

			<div class="two wide column"></div>
			<div class="twelve wide column">

				<div class="ui two column very relaxed grid">
					<div class="column">


						<select name="article" class="ui dropdown" id="selectArticle">

						</select>
						<button type="button" class="ui green animated button" onclick="ajouterArticle()">
							<div class="visible content">Ajouter</div>
							<div class="hidden content">
								<i class="right arrow icon"></i>
							</div>
						</button>

					</div>
					<div class="column">
						<table class="ui celled table" id="tableArticlesAjouter">
							<thead>
								<tr>
									<th>Article</th>
									<th>Poids (g)</th>
									<th>Quantité</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>

						<p>
							Poids total de la commande :<span id="poidsTotal">0,3</span> kg
						</p>

						<div id="buttonContainer" class="field ">
							<button class="ui  left floated red animated button" onclick="annulerCarton(<%=commandeATraiter.getNumero()%>)">
								<div class="visible content">Annuler</div>
								<div class="hidden content">
									<i class="cancel icon"></i>
								</div>
							</button>

							<button class="ui right floated green animated button" onclick="validerCarton()">
								<div class="visible content">Valider carton</div>
								<div class="hidden content">
									<i class="checkmark icon"></i>
								</div>
							</button>
						</div>
					</div>

				</div>

			</div>
			<div class="two wide column">

			</div>
		</div>

	</div>
</body>
</html>
