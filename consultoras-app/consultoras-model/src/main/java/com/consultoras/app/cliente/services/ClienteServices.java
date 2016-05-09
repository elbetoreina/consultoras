package com.consultoras.app.cliente.services;

import java.util.List;

import javax.ejb.Local;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.exception.ClienteNotFoundException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.common.exception.FieldNotValidException;

@Local
public interface ClienteServices {
	
	Cliente testAddCliente() throws FieldNotValidException, ClienteExistentException;

	Cliente add(Cliente cliente) throws FieldNotValidException, ClienteExistentException;
	
	void update(Cliente cliente) throws FieldNotValidException, ClienteNotFoundException;
	
	void delete(Cliente cliente) throws ClienteNotFoundException;
	
	Cliente findById(Long id) throws ClienteNotFoundException;
	
	List<Cliente> findAll();

	void testUpdateCliente() throws FieldNotValidException, ClienteNotFoundException;
}
