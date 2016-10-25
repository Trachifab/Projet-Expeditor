package fr.eni.expeditor.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import fr.eni.expeditor.entity.Utilisateur;

@Stateless
public class GestionUtilisateurBean extends AbstractService {

	public void ajouter(Utilisateur user) {

		getEntityManager().merge(user);

	}

	public List<Utilisateur> consulter() {

		Query query = getEntityManager().createNamedQuery(Utilisateur.MA_REQUETE);

		return query.getResultList();
	}

}
