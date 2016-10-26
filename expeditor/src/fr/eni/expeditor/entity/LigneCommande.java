package fr.eni.expeditor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class LigneCommande {

	@EmbeddedId
	private IdLigneCommande id;

	@MapsId("commande_numero")
	@ManyToOne
	private Commande commande;

	@MapsId("article_id")
	@ManyToOne
	private Article article;

	private Integer quantite;

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public IdLigneCommande getId() {
		return id;
	}

	public void setId(IdLigneCommande id) {
		this.id = id;
	}

}
