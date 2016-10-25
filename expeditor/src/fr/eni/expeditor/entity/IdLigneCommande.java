package fr.eni.expeditor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdLigneCommande implements Serializable {

	@Column(name = "article_id")
	private Integer article_id;

	@Column(name = "commande_numeros")
	private Integer commande_numeros;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article_id == null) ? 0 : article_id.hashCode());
		result = prime * result + ((commande_numeros == null) ? 0 : commande_numeros.hashCode());
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
		if (article_id == null) {
			if (other.article_id != null)
				return false;
		} else if (!article_id.equals(other.article_id))
			return false;
		if (commande_numeros == null) {
			if (other.commande_numeros != null)
				return false;
		} else if (!commande_numeros.equals(other.commande_numeros))
			return false;
		return true;
	}

}
