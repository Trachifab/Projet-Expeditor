package fr.eni.expeditor.service;

import javax.persistence.*;
import java.util.List;

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

	public List<?> consulter(Class type) {

		Table annotation = (Table) type.getAnnotation(Table.class);

		String value = null;
		if (annotation != null) {
			value = annotation.name();
		}
		if (value == null) {
			value = type.getSimpleName();
		}
		Query query = getEntityManager().createQuery("SELECT u FROM " + value + " u");

		return query.getResultList();
	}


	protected void raffraichirCache()
	{
		factory.getCache().evictAll();
	}

}
