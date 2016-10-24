package fr.eni.expeditor.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur {

	@Id
	private BigInteger identite;

	private String login;

	private String password;

	public BigInteger getIdentite() {
		return identite;
	}

	public void setIdentite(BigInteger identite) {
		this.identite = identite;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
