package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Article;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d1502doreyf on 25/10/2016.
 */
@Stateless
public class GestionArticleBean extends AbstractService {

	public List<Article> rechercherTous() {

		Query query = getEntityManager().createQuery("SELECT a FROM Article a WHERE a.dateArchive is null");
		return query.getResultList();

	}

	public Article rechercherParIdentifiant(Integer id) {
		return getEntityManager().find(Article.class, id);
	}

	public Article rechercherParIdentifiantExterne(Article article) {
		Query q = getEntityManager().createNamedQuery("rechercherArticleParIdentifiantExterne");
		q.setParameter("idExterne", article.getIdExterne());
		try {
			return (Article) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	/**
	 * Insert ou met à jour les articles en base (gère l'afféctation de
	 * l'idExterne)
	 * 
	 * @param article
	 * @return l'article persisté
	 */
	public Article enregistrerArticle(Article article) {

		if (article.getId() == null) {

			article.setIdExterne(article.getLibelle());

			getEntityManager().persist(article);
		} else {
			article = getEntityManager().merge(article);
		}

		return article;
	}

	/**
	 * Valide les différentes règles de gestion d'enregistrement des articles
	 * 
	 * @param article
	 * @return liste des erreurs
	 */
	public List<String> verifierRG(Article article) {

		List<String> erreurs = new ArrayList<String>();

		if (article.getLibelle() == null || article.getLibelle().isEmpty()) {
			erreurs.add("Le libelle est obligatoire");
		} else {
			if (article.getLibelle().length() > 50) {
				erreurs.add("Le libelle est obligatoire");
			}
		}

		if (article.getPoids() == null) {
			erreurs.add("Le poids est obligatoire");
		} else {
			if (article.getPoids() < 0) {
				erreurs.add("Le poids doit être supérieur ou égale à 0");
			}
		}

		return erreurs;

	}

}
