package com.consultoras.app.consultora.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.consultoras.app.consultora.model.Consultora;

public class ConsultoraRepository {
	
	@PersistenceContext
	public EntityManager em;
	
	public Consultora add(Consultora consultora){
		em.persist(consultora);
		return consultora;
	}

}
