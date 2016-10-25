package fr.eni.expeditor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(@NamedQuery(name=Utilisateur.MA_REQUETE,query=Utilisateur.MA_REQUETE_SQL))
public class Utilisateur {

	public static final String MA_REQUETE ="Utilisateur.MA_REQUETE";
	public static final String MA_REQUETE_SQL ="Select u FROM Utilisateur u";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer identite;

	private String login;

	private String password;

	public Integer getIdentite() {
		return identite;
	}

	public void setIdentite(Integer identite) {
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
