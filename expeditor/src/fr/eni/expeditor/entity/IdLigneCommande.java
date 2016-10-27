package fr.eni.expeditor.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdLigneCommande implements Serializable {

	@Column(name = "article_id")
	private Integer article_id;

	@Column(name = "commande_numero")
	private Integer commande_numero;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article_id == null) ? 0 : article_id.hashCode());
		result = prime * result + ((commande_numero == null) ? 0 : commande_numero.hashCode());
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
		if (commande_numero == null) {
			if (other.commande_numero != null)
				return false;
		} else if (!commande_numero.equals(other.commande_numero))
			return false;
		return true;
	}

}
