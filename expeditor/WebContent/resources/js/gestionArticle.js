(function ($) {
    $(document).ready(function () {
        // Initialisation du tri sur les tables
        $('table').tablesort();
    });
})(jQuery);

function afficherModal(id) {

	$('#' + id).modal('show');
}

function fermerModale(id){
	$('#' + id).modal('hide');
}

function afficherModalArchiver(idArticle) {
	$('#suppForm [name=artId]').val(idArticle);

	afficherModal('popupValidationArchivage')
}

function afficherModalArticle(idArticle, articleLibelle, articleDescription,
		articlePoids) {

	$('#popupArticle [name=articleId]').val(idArticle);
	$('#popupArticle [name=articleLibelle]').val(articleLibelle);
	$('#popupArticle [name=articleDescription]').html(articleDescription);
	$('#popupArticle [name=articlePoids]').val(articlePoids);

	afficherModal('popupArticle')

}



