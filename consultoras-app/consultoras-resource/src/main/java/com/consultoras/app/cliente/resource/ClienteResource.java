package com.consultoras.app.cliente.resource;

import static com.consultoras.app.common.model.StandardsOperationResults.*;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.exception.ClienteNotFoundException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.services.ClienteServices;
import com.consultoras.app.common.exception.FieldNotValidException;
import com.consultoras.app.common.json.JsonUtils;
import com.consultoras.app.common.json.JsonWriter;
import com.consultoras.app.common.json.OperationResultJsonWriter;
import com.consultoras.app.common.model.HttpCode;
import com.consultoras.app.common.model.OperationResult;
import com.consultoras.app.common.model.ResourceMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

		logger.debug("Retornando el resultado de la operacion despues de agregar al cliente: {}", result);
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

		logger.debug("Retornando el resultado de la operacion despues de agregar al cliente: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();

	}
	
	public Response delete(final Long id){
		logger.debug("Borrando el cliente id {}", id);
		
		HttpCode httpCode = HttpCode.OK;
		OperationResult result;
		
		
		try{
			Cliente cliente = clienteServices.findById(id);
			clienteServices.delete(cliente);
			result = OperationResult.success(JsonUtils.getJsonElementWithId(cliente.getId()));
		}
		catch (final ClienteNotFoundException e) {
			logger.error("cliente no encontrado", e);
			httpCode = HttpCode.NOT_FOUND;
			result = getOperationResultNotFound(RESOURCE_MESSAGE);
		}
		
		logger.debug("Retornado el resultado de la operacion despues de eliminar el cliente: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();
	}

	public Response update(final Long id, final String body) {
		logger.debug("Actualizando el cliente {} con los valores {}", id, body);

		HttpCode httpCode = HttpCode.OK;
		OperationResult result;

		try {
			final Cliente cliente = clienteJsonConverter.convertFrom(body);
			cliente.setId(id);

			try {
				clienteServices.update(cliente);
				result = OperationResult.success();
			} catch (final FieldNotValidException e) {
				logger.error("Uno de los valores del cliente creado no es valido", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultInvalidField(RESOURCE_MESSAGE, e);
			} catch (final ClienteExistentException e) {
				logger.error("Ya existe un cliente con los valores asignados: {}", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultExistent(RESOURCE_MESSAGE, "primerNombre, primerApellido, celular");
			} catch (final ClienteNotFoundException e) {
				logger.error("cliente no encontrado", e);
				httpCode = HttpCode.NOT_FOUND;
				result = getOperationResultNotFound(RESOURCE_MESSAGE);
			}
		} catch (final Exception e) {
			logger.error("Existen valores invalidos: {}", e);
			httpCode = HttpCode.VALIDATION_ERROR;
			result = getOperationResultIllegalArgument(RESOURCE_MESSAGE, e);
		}

		logger.debug("Retornando el resultado de la operacion despues de actualizar al cliente: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();
	}

	// This needs to have the same body as update Method *EXCEPTING MARKED*
	public Response updateTest(final Long id, final String body) {
		logger.debug("Actualizando el cliente {} con los valores {}", id, body);

		HttpCode httpCode = HttpCode.OK;
		OperationResult result;

		try {
			final Cliente cliente = clienteJsonConverter.convertFrom(body);
			cliente.setId(id);

			try {

				/*
				 * EXCEPTING THIS Original: cliente =
				 * clienteServices.update(cliente);
				 */
				clienteServices.testUpdateCliente();

				result = OperationResult.success();
			} catch (final FieldNotValidException e) {
				logger.error("Uno de los valores del cliente creado no es valido", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultInvalidField(RESOURCE_MESSAGE, e);
			} catch (final ClienteExistentException e) {
				logger.error("Ya existe un cliente con los valores asignados: {}", e);
				httpCode = HttpCode.VALIDATION_ERROR;
				result = getOperationResultExistent(RESOURCE_MESSAGE, "primerNombre, primerApellido, celular");
			} catch (final ClienteNotFoundException e) {
				logger.error("No existe un cliente con el id especificado", e);
				httpCode = HttpCode.NOT_FOUND;
				result = getOperationResultNotFound(RESOURCE_MESSAGE);
			}
		} catch (final Exception e) {
			logger.error("Existen valores invalidos: {}", e);
			httpCode = HttpCode.VALIDATION_ERROR;
			result = getOperationResultIllegalArgument(RESOURCE_MESSAGE, e);
		}

		logger.debug("Retornando el resultado de la operacion despues de actualizar al cliente: {}", result);
		return Response.status(httpCode.getCode()).entity(OperationResultJsonWriter.toJson(result)).build();
	}

	public Response findById(final Long id) {
		logger.debug("Buscando cliente: {}", id);
		ResponseBuilder responseBuilder;
		try {
			final Cliente cliente = clienteServices.findById(id);
			final OperationResult result = OperationResult.success(clienteJsonConverter.convertToJsonElement(cliente));
			responseBuilder = Response.status(HttpCode.OK.getCode()).entity(OperationResultJsonWriter.toJson(result));
			logger.debug("Cliente encontrado: {}", cliente);
		} catch (final ClienteNotFoundException e) {
			logger.error("No existe un cliente con el id ", id);
			responseBuilder = Response.status(HttpCode.NOT_FOUND.getCode());
		}

		return responseBuilder.build();
	}

	public Response findAll() {
		logger.debug("Buscando todos los clientes");

		final List<Cliente> clientes = clienteServices.findAll();

		logger.debug("Encontrados {} clientes", clientes.size());

		final JsonElement jsonWithPagingAndEntries = getJsonElementWithPagingAndEntries(clientes);

		return Response.status(HttpCode.OK.getCode()).entity(JsonWriter.writeToString(jsonWithPagingAndEntries))
				.build();
	}

	private JsonElement getJsonElementWithPagingAndEntries(final List<Cliente> categories) {
		final JsonObject jsonWithEntriesAndPaging = new JsonObject();

		final JsonObject jsonPaging = new JsonObject();
		jsonPaging.addProperty("totalRecords", categories.size());

		jsonWithEntriesAndPaging.add("paging", jsonPaging);
		jsonWithEntriesAndPaging.add("entries", clienteJsonConverter.convertToJsonElement(categories));

		return jsonWithEntriesAndPaging;
	}

}
