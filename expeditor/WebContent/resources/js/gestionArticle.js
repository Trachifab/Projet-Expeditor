function afficherModal(id) {

	$('#' + id).modal('show');

}

function afficherModalArchiver(idArticle) {
	$('#popupValidationArchivage [name=articleId]').val(idArticle);

	afficherModal('popupValidationArchivage')

}

function afficherModalArticle(idArticle, articleLibelle, articleDescription,
		articlePoids) {

	$('#popupArticle [name=articleId]').val(idArticle);
	$('#popupArticle [name=articleLibelle]').val(articleLibelle);
	$('#popupArticle [name=articleDescription]').html(articleDescription);
	$('#popupArticle [name=articlePoids]').val(articlePoids);

	if (idArticle == '') {
		$('#popupArticle [name=action]').html('Ajouter')
		$('#popupArticle .header').html('Ajouter article')
		$('#popupArticle [name=action]').val('ajouter')
	} else {
		$('#popupArticle [name=action]').html('Modifier')
		$('#popupArticle .header').html('Modifier article')
		$('#popupArticle [name=action]').val('modifier')
	}
	afficherModal('popupArticle')

}