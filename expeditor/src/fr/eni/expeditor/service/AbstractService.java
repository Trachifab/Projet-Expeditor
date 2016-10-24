package fr.eni.expeditor.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceUnit;

public class AbstractService {

	@PersistenceUnit(unitName = "expeditor")
	private EntityManagerFactory factory;

	private EntityManager em;

	protected EntityManager getEntityManager() {
		if (em == null) {
			em = factory.createEntityManager();
			em.setFlushMode(FlushModeType.COMMIT);
		}
		return em;

	}

}
