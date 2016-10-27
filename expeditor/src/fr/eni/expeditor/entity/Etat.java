package fr.eni.expeditor.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Etat {

	@Id
	private String code;

	private String libelle;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
