package de.dominikschadow.webappsecurity;

import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * This is the stateless EJB that produced our {@link EntityManager} instance.
 * This EJB has an entity manager injected and we return it to CDI in our
 * {@link Produces} annotated method. We qualify the producer with the
 * {@link DataRepository} qualifier.
 * 
 */
@Stateless
public class DataRepositoryProducer {
	
	
	private EntityManager entityManager;
	
	@Produces @DataRepository @ConversationScoped
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
