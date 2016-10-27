<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="fr.eni.expeditor.entity.Article"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ page import="java.util.List"%>

<html>
<head>
<title>Gestion des articles</title>

<script type="text/javascript"
	src="resources/JQuery/jquery-3.1.1.min.js">
	
</script>

<link rel="stylesheet" type="text/css"
	href="resources/semanticUI/semantic.min.css">
<script src="resources/semanticUI/semantic.min.js"></script>

<link href="resources/stylesheets/gestionArticle.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="resources/js/gestionArticle.js"></script>
</head>
<body>

	<!-- Inclusion du menu -->
	<%@include file="/WEB-INF/views/partial/menu.jsp"%>


	<div class="ui equal width center aligned padded grid">
		<div class="row">
			<div class="two wide column"></div>
			<div class="twelve wide column">

				<%
					List<Article> lstArticles = (List<Article>) request.getAttribute("lstArticle");

					if (lstArticles == null || lstArticles.isEmpty()) {
				%>
				<p>Pas d'article trouvé.</p>

				<%
					} else {
				%>


				<!-- Tableaux liste des articles -->
				<table class="ui selectable celled table">
					<thead>
						<tr>
							<th>Libellé</th>
							<th>Description</th>
							<th>Poids (gr)</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Article article : lstArticles) {
						%>
						<tr>

							<td><%=article.getLibelle()%></td>
							<td><%=article.getDescription()%></td>
							<td><%=article.getPoids()%></td>
							<td><button type="button" class="ui button"
									onclick="afficherModalArticle('<%=article.getId()%>',
					'<%=StringEscapeUtils.escapeEcmaScript(article.getLibelle())%>',
					'<%=StringEscapeUtils.escapeEcmaScript(article.getDescription())%>',
					'<%=article.getPoids()%>')">
									<i class="edit icon"></i>
								</button>

								<button class="ui button"
									onclick="afficherModalArchiver('<%=article.getId()%>')">
									<i class="archive icon"></i>
								</button></td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
				<button type="button" value="ajouter" class="ui green button"
					onclick="afficherModalArticle('','','','','')">
					<i class="add square icon"></i> ajouter
				</button>



			</div>
			<div class="two wide column"></div>
		</div>
	</div>
	<!-- popup d'ajout et de modifications des articles -->
	<div id="popupArticle" class="ui small modal">

		<div class="header">Ajouter article</div>

		<form class="ui form" method="post" action="GestionArticleServlet">
			<input type="hidden" name="articleId" id="articleId" />
			<div class="content ">


				<div class="field">
					<label>Libelle</label> <input type="text" name="articleLibelle" />
				</div>
				<div class="field">
					<label>Description</label>
					<textarea name="articleDescription" rows="2"></textarea>
				</div>
				<div class="field">
					<label>Poids</label> <input type="number" name="articlePoids" />
				</div>
			</div>

			<div class="actions">
				<button type="submit" class="ui button positive" value="ajouter"
					name="action">Enregistrer</button>

				<button class="ui button cancel">Annuler</button>


			</div>
		</form>
	</div>
	<%
		List<String> erreurs = (List<String>) request.getAttribute("erreurs");

		if (erreurs != null && erreurs.size() > 0) {
	%>
	<!-- popup d'affichage des erreurs -->
	<div id="popupErreurs" class="ui small modal">
		<div class="header">Erreur de validation</div>

		<div class="content ">
			<p>
				<%
					for (String erreur : erreurs) {
				%>
				<%=erreur%><br />
				<%
					}
				%>
			</p>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(afficherModal('popupErreurs'));
	</script>

	<%
		}
	%>


	<!-- popup d'affichage des erreurs -->
	<div id="popupValidationArchivage" class="ui basic modal">
		<form action="GestionArticleServlet" method="post">
			<input type="hidden" name="articleId" id="articleId" />
			<div class="header">
				<p>
					Archiver
				<p>
			</div>
			<div class="description">
				<p>Voulez vous archiver l'article ?</p>
			</div>
			<div class="actions">
				<button type="submit" class="ui button positive inverted"
					value="archiver" name="action">Oui</button>

				<button class="ui button cancel inverted">Non</button>


			</div>
		</form>
	</div>

</body>
</html>
