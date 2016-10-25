package fr.eni.expeditor.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Embeddable
public class IdLigneCommande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4039358138295614403L;

	@ManyToOne
	private Commande commande;

	@ManyToOne
	private Article article;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + ((commande == null) ? 0 : commande.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdLigneCommande other = (IdLigneCommande) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (commande == null) {
			if (other.commande != null)
				return false;
		} else if (!commande.equals(other.commande))
			return false;
		return true;
	}

}
