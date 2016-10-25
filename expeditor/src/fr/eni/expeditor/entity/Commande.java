package fr.eni.expeditor.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commande {

	@Id
	private Integer numeros;

	private Date dateComande;

	private Date dateTraitment;

	@ManyToOne
	private Client client;

	/*
	 * @OneToMany private List<LigneCommande> lstCommande;
	 */

	@ManyToOne
	private Collaborateur collaborateur;

	// public List<LigneCommande> getLstCommande() {
	// return lstCommande;
	// }
	//
	// public void setLstCommande(List<LigneCommande> lstCommande) {
	// this.lstCommande = lstCommande;
	// }

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getNumeros() {
		return numeros;
	}

	public void setNumeros(Integer numeros) {
		this.numeros = numeros;
	}

	public Date getDateComande() {
		return dateComande;
	}

	public void setDateComande(Date dateComande) {
		this.dateComande = dateComande;
	}

	public Date getDateTraitment() {
		return dateTraitment;
	}

	public void setDateTraitment(Date dateTraitment) {
		this.dateTraitment = dateTraitment;
	}

}
