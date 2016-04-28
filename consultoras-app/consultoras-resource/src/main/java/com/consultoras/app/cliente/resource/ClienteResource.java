package com.consultoras.app.cliente.resource;

import static com.consultoras.app.common.model.StandardsOperationResults.*;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.services.ClienteServices;
import com.consultoras.app.common.exception.FieldNotValidException;
import com.consultoras.app.common.json.JsonUtils;
import com.consultoras.app.common.json.OperationResultJsonWriter;
import com.consultoras.app.common.model.HttpCode;
import com.consultoras.app.common.model.OperationResult;
import com.consultoras.app.common.model.ResourceMessage;

public class ClienteResource {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final ResourceMessage RESOURCE_MESSAGE = new ResourceMessage("cliente");

	ClienteServices clienteServices;

	ClienteJsonConverter clienteJsonConverter;

	// This needs to have the same body as add Method *EXCEPTING MARKED*
	public Response addTest(final String body) {

		logger.debug("Agregando un nuevo cliente: {}", body);

		Cliente cliente = new Cliente();

		HttpCode httpCode = HttpCode.CREATED;
		OperationResult result;

		try {
			cliente = clienteJsonConverter.convertFrom(body);

			try {
				/*
				 * EXCEPTING THIS Original: cliente =
				 * clienteServices.add(cliente);
				 */
				cliente = clienteServices.testAddCliente();
				result = OperationResult.success(JsonUtils.getJsonElementWithId(cliente.getId()));
			} catch (final FieldNotValidException e) {
				logger.error("Uno de los valores del cliente creado no es valido", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultInvalidField(RESOURCE_MESSAGE, e);
			} catch (final ClienteExistentException e) {
				logger.error("Ya existe un cliente con los valores asignados: {}", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultExistent(RESOURCE_MESSAGE, "primerNombre, primerApellido, celular");
			} 
		} catch (final Exception e) {
			logger.error("Existen valores invalidos: {}", e);
			httpCode = HttpCode.VALIDATION_ERROR;
			result = getOperationResultIllegalArgument(RESOURCE_MESSAGE, e);
		} 

		logger.debug("Returning the operation result after adding category: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();

	}

	public Response add(final String body) {

		logger.debug("Agregando un nuevo cliente: {}", body);

		Cliente cliente = new Cliente();

		HttpCode httpCode = HttpCode.CREATED;
		OperationResult result;

		try {
			cliente = clienteJsonConverter.convertFrom(body);

			try {
				cliente = clienteServices.add(cliente);				
				result = OperationResult.success(JsonUtils.getJsonElementWithId(cliente.getId()));
			} catch (final FieldNotValidException e) {
				logger.error("Uno de los valores del cliente creado no es valido", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultInvalidField(RESOURCE_MESSAGE, e);
			} catch (final ClienteExistentException e) {
				logger.error("Ya existe un cliente con los valores asignados: {}", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultExistent(RESOURCE_MESSAGE, "primerNombre, primerApellido, celular");
			} 
		} catch (final Exception e) {
			logger.error("Existen valores invalidos: {}", e);
			httpCode = HttpCode.VALIDATION_ERROR;
			result = getOperationResultIllegalArgument(RESOURCE_MESSAGE, e);
		} 

		logger.debug("Returning the operation result after adding category: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();

	}

}
