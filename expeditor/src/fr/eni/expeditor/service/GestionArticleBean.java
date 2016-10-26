package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Article;
import fr.eni.expeditor.entity.Collaborateur;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Created by d1502doreyf on 25/10/2016.
 */
@Stateless
public class GestionArticleBean extends AbstractService {

	public List<Article> rechercherTous() {
		return (List<Article>) consulter(Article.class);
	}

	public Article rechercherParIdentifiant(Article article) {
		return getEntityManager().find(Article.class, article);
	}

	public Article rechercherParIdentifiantExterne(Article article) {
		return getEntityManager().find(Article.class, article.getIdExterne());
	}

	public Article enregistrerArticle(Article article) {

		if (article.getId() == null) {

			article.setIdExterne(article.getLibelle());

			getEntityManager().persist(article);
		} else {
			article = getEntityManager().merge(article);
		}

		return article;
	}

	public List<String> verifierRG(Article article) {

		List<String> erreurs = new ArrayList<String>();

		if (article.getLibelle() == null || article.getLibelle().isEmpty()) {
			erreurs.add("Le libelle est obligatoire");
		} else {
			if (article.getLibelle().length() > 50) {
				erreurs.add("Le libelle est obligatoire");
			}
		}
		
		if (article.getPoids() == null ) {
			erreurs.add("Le poids est obligatoire");
		} else {
			if (article.getPoids() < 0) {
				erreurs.add("Le poids doit être supérieur ou égale à 0");
			}
		}
		
		
		return erreurs;

	}

}
