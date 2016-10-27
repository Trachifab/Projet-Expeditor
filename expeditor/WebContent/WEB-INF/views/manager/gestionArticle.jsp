<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="fr.eni.expeditor.entity.Article"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ page import="java.util.List"%>

<html>
<head>
<title>Gestion des articles</title>

    <!-- JQuery -->
    <script src="${pageContext.request.contextPath}/resources/JQuery/jquery-3.1.1.min.js"></script>


    <!-- Expeditor scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/gestionArticle.js"></script>

    <!-- Expeditor stylesheets -->
    <link rel="stylesheet" type="text/css" href="resources/stylesheets/gestionEmploye.css">

    <!-- Semantic stylesheets -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.css">
    <script src="${pageContext.request.contextPath}/resources/semanticUI/semantic.min.js"></script>

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
						<tr class="positive">

							<td><%=article.getLibelle()%></td>
							<td><%=article.getDescription()%></td>
							<td><%=article.getPoids()%></td>
							<td>
                                <div class="field">
                                    <button class="small ui icon brown button" type="button"
                                            onclick="afficherModalArticle('<%=article.getId()%>',
                                                    '<%=StringEscapeUtils.escapeEcmaScript(article.getLibelle())%>',
                                                    '<%=StringEscapeUtils.escapeEcmaScript(article.getDescription())%>',
                                                    '<%=article.getPoids()%>')">
                                        <i class="small edit icon"></i>
                                    </button>

                                    <button class="ui icon red small button" onclick="afficherModalArchiver('<%=article.getId()%>')">
                                        <i class="small trash icon"></i>
                                    </button>
                                </div>
                            </td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<%
					}
				%>
				<button type="button" value="ajouter" class="ui green center aligned animated button" onclick="afficherModalArticle('','','','','')">
					<div class="visible content">Ajouter</div>
					<div class="hidden content">
						<i class="add icon"></i>
					</div>
				</button>



			</div>
			<div class="two wide column"></div>
		</div>
	</div>

    <!-- popup d'ajout et de modifications des employés -->
    <div id="popupArticle" class="ui small modal">
        <form class="ui equal width form" method="post" action="GestionArticleServlet">
            <input type="hidden" name="articleId" id="articleId" />
                <div class="fields">
                    <div class="field">
                        <h1 class="ui center header">Ajouter un article</h1>
                    </div>
                </div>
                <div class="fields">
                    <div class="field">
                        <label>Libelle</label> <input type="text" name="articleLibelle" />
                    </div>
                    <div class="field">
                        <label>Poids</label> <input type="number" name="articlePoids" />
                    </div>
                </div>
                <div class="fields">
                    <div class="field">
                        <label>Description</label>
                        <textarea name="articleDescription"></textarea>
                    </div>
                </div>
                <div class="fields">
                    <div class="field">
                        <button class="ui green right floated animated button" type="submit" name="action" value="ajouter" >
                            <div class="visible content">Valider</div>
                            <div class="hidden content">
                                <i class="checkmark icon"></i>
                            </div>
                        </button>
                        <button class="ui red left floated animated button" onclick="fermerModale('popupArticle')">
                            <div class="visible content">Annuler</div>
                            <div class="hidden content">
                                <i class="remove icon"></i>
                            </div>
                        </button>

                    </div>
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

	<!-- modale de suppression -->
	<div class="ui basic modal" id="popupValidationArchivage">
		<i class="close icon"></i>
		<div class="header">
			<div class="flex-container field">
				<i class="huge trash icon"></i>
			</div>
			<div class="flex-container field">
				Archiver un article
			</div>
		</div>
		<div class="image content">
			<div class="flex-container description">
				<p class="flex-container">Voulez-vous vraiment archiver cet article ?</p>
			</div>
		</div>
		<div>
			<form class="form" id="suppForm" method="post" action="GestionArticleServlet">
				<input type="hidden" name="artId" id="artId" />
				<button class="ui left floated red basic inverted button" onclick="fermerModale('popupValidationArchivage')" >
					<i class="remove icon"></i>
					Non
				</button>
				<button class="ui right floated green basic inverted button" type="submit" name="action" value="archiver">
					<i class="checkmark icon"></i>
					Oui
				</button>
			</form>
		</div>
	</div>

</body>
</html>
