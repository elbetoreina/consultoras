package com.consultoras.app.cliente.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.consultoras.app.cliente.model.Cliente;


@Stateless
public class ClienteRepository {
	
	
	@PersistenceContext
	EntityManager em;
	
	public Cliente add(Cliente cliente){
		em.persist(cliente);
		return cliente;
	}

	public Cliente findById(Long id) {
		if (id == null) {
			return null;
		}
		return em.find(Cliente.class, id);
	}

	public void update(Cliente cliente) {
		em.merge(cliente);
	}
	
	public void delete(Cliente cliente){
		em.remove(cliente);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(String orderField) {
		return em.createQuery("Select e From Cliente e Order by e." + orderField).getResultList();
	}
	
	public boolean alreadyExists(Cliente cliente) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select 1 From Cliente e where e.primerNombre = :primerNombre And e.primerApellido = :primerApellido And e.celular = :celular");
		if (cliente.getId() != null) {
			jpql.append(" And e.id != :id");
		}

		Query query = em.createQuery(jpql.toString());
		query.setParameter("primerNombre", cliente.getPrimerNombre());
		query.setParameter("primerApellido", cliente.getPrimerApellido());
		query.setParameter("celular", cliente.getCelular());
		
		if (cliente.getId() != null) {
			query.setParameter("id", cliente.getId());
		}

		return query.setMaxResults(1).getResultList().size() > 0;
	}

	public boolean existsById(Long id) {
		return em.createQuery("Select 1 From Cliente e where e.id = :id")
				.setParameter("id", id)
				.setMaxResults(1)
				.getResultList().size() > 0;
	}

}
