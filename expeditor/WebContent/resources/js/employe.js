(function($) {
	$(document).ready(function() {
		initListeArticle();
		calculerPoids();
	});
})(jQuery);

function initListeArticle() {
	$.each(articles, function(i, article) {
		$('#selectArticle').append($('<option>', {
			value : article.id,
			text : article.libelle
		}));
	});

}

function ajouterArticle() {

	var idArticle = $('#selectArticle').val();

	var articleSelectionne = getArticle(idArticle);

	if (idArticle != "") {
		ajouterLigneArticle(articleSelectionne);
	}

}

function getArticle(idArticle) {

	var articleSelectionne;

	$.each(articles, function(i, article) {
		if (article.id == idArticle) {
			articleSelectionne = article;
		}
	});

	return articleSelectionne;
}

function ajouterLigneArticle(article) {

	//var htmlTable = $('#tableArticlesAjouter > tbody').html();

	var htmlTable = "<tr idarticle=\""
			+ article.id
			+ "\"><td><div class=\"ui ribbon label\">"
			+ article.libelle
			+ "</div></td><td>"
			+ article.poids
			+ "</td><td><input type=\"number\" onChange=\"afficherPoids()\" value=\"0\"/></td><td>"
			+ "<button type=\"button\" onClick=\"retirerLigne(" + article.id
			+ ")\"><i class=\"ban red icon\"></i></button></td></tr>";

	$('#tableArticlesAjouter > tbody').append(htmlTable)
	
	//$('#tableArticlesAjouter > tbody').html(htmlTable);

	retirerOption(article);

}

function retirerOption(article) {
	$('#selectArticle > option[value=' + article.id + ']').remove();
}

function calculerPoids() {
	var poidsTotal = 300;

	$('#tableArticlesAjouter tr[idarticle]').each(
			function(i) {
				var article = getArticle($(this).attr('idarticle'))
				var nb = $(
						'#tableArticlesAjouter tr[idarticle=' + article.id
								+ '] input[type=number]').val();
				poidsTotal += article.poids * nb;
			});

	return poidsTotal;
}

function afficherPoids() {
	$('#poidsTotal').html(calculerPoids() / 1000);
}

function retirerLigne(idArticle) {
	$('#tableArticlesAjouter tr[idarticle=' + idArticle + ']').remove();

	var article = getArticle(idArticle);

	$('#selectArticle').append($('<option>', {
		value : article.id,
		text : article.libelle
	}));

	afficherPoids();

}
