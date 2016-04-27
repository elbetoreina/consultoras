package com.consultoras.app.cliente.resource;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;
import static com.consultoras.app.commontests.utils.FileTestNameUtils.*;
import static com.consultoras.app.commontests.utils.JsonTestUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.exception.ClienteNotFoundException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.services.ClienteServices;
import com.consultoras.app.common.exception.FieldNotValidException;
import com.consultoras.app.common.model.HttpCode;
import com.consultoras.app.commontests.utils.ResourceDefinitions;

public class ClienteResourceUTest {

	private ClienteResource clienteResource;

	private static final String PATH_RESOURCE = ResourceDefinitions.CLIENTE.getResourceName();

	
	private ClienteServices clienteServices;

	@Before
	public void initTestCase() {		
		clienteResource = new ClienteResource();
		
		clienteServices = mock(ClienteServices.class);

		clienteResource.clienteServices = clienteServices;
		clienteResource.clienteJsonConverter = new ClienteJsonConverter();
	}

	@Test
	public void addValidCliente() {
		
		Cliente cliente = lucia();
		
		doReturn(clienteWithId(cliente, 1L)).when(clienteServices).testAddCliente();

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "newCliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.CREATED.getCode())));
		assertJsonMatchesExpectedJson(response.getEntity().toString(), "{\"id\": 1}");
	}
	
	@Test
	public void addExistentCliente() {
		
		when(clienteServices.testAddCliente()).thenThrow(new ClienteExistentException());

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"newCliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}
	
	
	@Test
	public void addClienteWithNullPrimerNombre() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerNombre", "El Primer Nombre del cliente aparece vacio o nulo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithNullPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}
	
	@Test
	public void addClienteWithNullPrimerApellido() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerApellido", "El Primer Apellido del cliente aparece vacio o nulo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithNullPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerApellido.json");
	}
	
	@Test
	public void addClienteWithNullDireccion() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("direccion", "La Direccion del cliente aparece vacia o nula"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithNullDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullDireccion.json");
	}
	
	@Test
	public void addClienteWithNullHoraLocalizacion() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("horaLocalizacion", "La Hora de Localizacion del cliente aparece vacia o nula"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithNullHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullHoraLocalizacion.json");
	}
	
	@Test
	public void addClienteWithInvalidColorCabello() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidColorCabello.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorCabello.json");
	}
	
	@Test
	public void addClienteWithInvalidColorOjos() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidColorOjos.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorOjos.json");
	}
	
	@Test
	public void addClienteWithInvalidFormaCara() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidFormaCara.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFormaCara.json");
	}
	
	@Test
	public void addClienteWithInvalidRangoEdad() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidRangoEdad.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidRangoEdad.json");
	}
	
	@Test
	public void addClienteWithInvalidTipoLabios() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidTipoLabios.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoLabios.json");
	}
	
	@Test
	public void addClienteWithInvalidTipoPiel() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidTipoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoPiel.json");
	}
	
	@Test
	public void addClienteWithInvalidTonoBase() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidTonoBase.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoBase.json");
	}
	
	@Test
	public void addClienteWithInvalidTonoPiel() {

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidTonoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoPiel.json");
	}
	
	@Test
	public void addClienteWithLongPrimerNombre() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerNombre", "El Primer Nombre del Cliente es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerNombre.json");
	}
	
	@Test
	public void addClienteWithLongSegundoNombre() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("segundoNombre", "El Segundo Nombre del Cliente es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoNombre.json");
	}
	
	@Test
	public void addClienteWithLongPrimerApellido() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerApellido", "El Primer Apellido del Cliente es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerApellido.json");
	}
	
	@Test
	public void addClienteWithShortSegundoApellido() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("segundoApellido", "El Segundo Apellido del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");
	}
	
	@Test
	public void addClienteWithShortApellidoCasada() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("apellidoCasada", "El Apellido de Casada del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}
	
	
	@Test
	public void addClienteWithShortPrimerNombre() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerNombre", "El Primer Nombre del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerNombre.json");
	}
	
	@Test
	public void addClienteWithShortSegundoNombre() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("segundoNombre", "El Segundo Nombre del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoNombre.json");
	}
	
	@Test
	public void addClienteWithShortPrimerApellido() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerApellido", "El Primer Apellido del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerApellido.json");
	}
	
	@Test
	public void addClienteWithShortSegundoApellido() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("segundoApellido", "El Segundo Apellido del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");
	}
	
	@Test
	public void addClienteWithShortApellidoCasada() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("apellidoCasada", "El Apellido de Casada del Cliente es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}
	
	
	
	private void assertJsonResponseWithFile(final Response response, final String fileName) {
		assertJsonMatchesFileContent(response.getEntity().toString(), getPathFileResponse(PATH_RESOURCE, fileName));
	}
	
	

}
