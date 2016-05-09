package com.consultoras.app.cliente.services.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.exception.ClienteNotFoundException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.repository.ClienteRepository;
import com.consultoras.app.cliente.services.ClienteServices;
import com.consultoras.app.common.exception.FieldNotValidException;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;

@Stateless
public class ClientServicesImpl implements ClienteServices {

	@Inject
	Validator validator;
	
	@Inject
	ClienteRepository clienteRepository;

	@Override
	public Cliente add(Cliente cliente) {
		
			validateCliente(cliente);

			return clienteRepository.add(cliente);
		
	}
	
	@Override
	public Cliente testAddCliente() {
		Cliente cliente = new Cliente();
		
		cliente = lucia();
		
		cliente = clienteWithId(cliente, 1L);

		return cliente;
	}
	

	@Override
	public void update(Cliente cliente) {
		validateCliente(cliente);

		if (!clienteRepository.existsById(cliente.getId())) {
			throw new ClienteNotFoundException();
		}

		clienteRepository.update(cliente);
	}
	
	@Override
	public void testUpdateCliente() {
		Cliente cliente = new Cliente();
		
		cliente = lucia();
		
		cliente.setCelular("4769-2191");
		
		clienteRepository.update(cliente);
	}

	@Override
	public void delete(Cliente cliente) throws ClienteNotFoundException {

		if (!clienteRepository.existsById(cliente.getId())) {
			throw new ClienteNotFoundException();
		}

		clienteRepository.delete(cliente);

	}

	@Override
	public Cliente findById(Long id) throws ClienteNotFoundException {

		final Cliente cliente = clienteRepository.findById(id);

		if (cliente == null) {
			throw new ClienteNotFoundException();
		}

		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll("primerApellido");
	}

	private void validateCliente(final Cliente cliente) {
		validateClienteFields(cliente);

		if (clienteRepository.alreadyExists(cliente)) {
			throw new ClienteExistentException();
		}
	}

	private void validateClienteFields(final Cliente cliente) {
		final Set<ConstraintViolation<Cliente>> errors = validator.validate(cliente);
		final Iterator<ConstraintViolation<Cliente>> itErrors = errors.iterator();

		if (itErrors.hasNext()) {
			final ConstraintViolation<Cliente> violation = itErrors.next();
			throw new FieldNotValidException(violation.getPropertyPath().toString(), violation.getMessage());
		}
	}

	

}
