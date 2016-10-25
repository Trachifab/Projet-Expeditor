package fr.eni.expeditor.service;

import javax.ejb.Stateless;

import fr.eni.expeditor.entity.Utilisateur;

@Stateless
public class GestionUtilisateurBean extends AbstractService {

	public void ajouter(Utilisateur user) {

		getEntityManager().merge(user);

	}

	

}
