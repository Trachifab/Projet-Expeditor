
function initListeArticle()
{
	$.each(articles, function (i, article) {
	    $('#selectArticle').append($('<option>', { 
	        value: article.id,
	        text : article.libelle 
	    }));
	});
	
}

$(document).ready(initListeArticle());