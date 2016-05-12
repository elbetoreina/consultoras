package com.consultoras.app.commontests.cliente;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.consultoras.app.cliente.services.ClienteServices;

@Path("/DB/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResourceDB {

	@Inject
	private ClienteServices clienteServices;

	@POST
	public void addAll() {
		allClientes().forEach(clienteServices::add);
	}

}