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

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "newCliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}

	@Test
	public void addClienteWithNullConsultoraId() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("consultoraId", "may not be null"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullConsultoraId.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullConsultoraId.json");
	}

	@Test
	public void updateClienteWithNullConsultora() {

		doThrow(new FieldNotValidException("consultora", "may not be null")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullConsultoraId.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullConsultoraId.json");
	}

	@Test
	public void addClienteWithNullPrimerNombre() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("primerNombre", "may not be null"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}

	@Test
	public void addClienteWithNullPrimerApellido() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("primerApellido", "may not be null"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerApellido.json");
	}

	@Test
	public void addClienteWithNullDireccion() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("direccion", "may not be null"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullDireccion.json");
	}

	@Test
	public void addClienteWithNullHoraLocalizacion() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("horaLocalizacion", "may not be null"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullHoraLocalizacion.json");
	}

	@Test
	public void addClienteWithInvalidColorCabello() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorCabello.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorCabello.json");
	}

	@Test
	public void addClienteWithInvalidColorOjos() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorOjos.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorOjos.json");
	}

	@Test
	public void addClienteWithInvalidFormaCara() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFormaCara.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFormaCara.json");
	}

	@Test
	public void addClienteWithInvalidRangoEdad() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidRangoEdad.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidRangoEdad.json");
	}

	@Test
	public void addClienteWithInvalidTipoLabios() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoLabios.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoLabios.json");
	}

	@Test
	public void addClienteWithInvalidTipoPiel() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoPiel.json");
	}

	@Test
	public void addClienteWithInvalidTonoBase() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoBase.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoBase.json");
	}

	@Test
	public void addClienteWithInvalidTonoPiel() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoPiel.json");
	}

	@Test
	public void addClienteWithLongPrimerNombre() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("primerNombre", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerNombre.json");
	}

	@Test
	public void addClienteWithLongSegundoNombre() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("segundoNombre", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoNombre.json");
	}

	@Test
	public void addClienteWithLongPrimerApellido() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("primerApellido", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerApellido.json");
	}

	@Test
	public void addClienteWithLongSegundoApellido() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("segundoApellido", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoApellido.json");
	}

	@Test
	public void addClienteWithLongApellidoCasada() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("apellidoCasada", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongApellidoCasada.json");
	}

	@Test
	public void addClienteWithLongHoraLocalizacion() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("horaLocalizacion", "size must be between 2 and 20"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongHoraLocalizacion.json");
	}

	@Test
	public void addClienteWithShortPrimerNombre() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("primerNombre", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerNombre.json");
	}

	@Test
	public void addClienteWithShortSegundoNombre() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("segundoNombre", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoNombre.json");
	}

	@Test
	public void addClienteWithShortPrimerApellido() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("primerApellido", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerApellido.json");
	}

	@Test
	public void addClienteWithShortSegundoApellido() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("segundoApellido", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");
	}

	@Test
	public void addClienteWithShortApellidoCasada() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("apellidoCasada", "size must be between 2 and 100"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}

	@Test
	public void addClienteWithShortHoraLocalizacion() {

		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("horaLocalizacion", "size must be between 2 and 20"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortHoraLocalizacion.json");
	}

	@Test
	public void addClienteWithShortDireccion() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("direccion", "size must be between 2 and 1000"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortDireccion.json");
	}

	@Test
	public void addClienteWithLongDireccion() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("direccion", "size must be between 2 and 1000"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongDireccion.json");
	}

	@Test
	public void addClienteWithInvalidEmail() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("email", "not a well-formed email address"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidEmail.json");
	}

	@Test
	public void addClienteWithLongEmail() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("email", "not a well-formed email address"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongEmail.json");
	}

	@Test
	public void addClienteWithShortCelular() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("celular", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortCelular.json");
	}

	@Test
	public void addClienteWithLongCelular() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("celular", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongCelular.json");
	}

	@Test
	public void addClienteWithShortTelefonoCasa() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoCasa", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoCasa.json");
	}

	@Test
	public void addClienteWithLongTelefonoCasa() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoCasa", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoCasa.json");
	}

	@Test
	public void addClienteWithShortTelefonoOficina() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoOficina", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficina.json");
	}

	@Test
	public void addClienteWithLongTelefonoOficina() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoOficina", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficina.json");
	}

	@Test
	public void addClienteWithShortTelefonoOficinaExtension() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoOficinaExtension", "size must be between 1 and 10"));

		final Response response = clienteResource.addTest(
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficinaExtension.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficinaExtension.json");
	}

	@Test
	public void addClienteWithLongTelefonoOficinaExtension() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoOficinaExtension", "size must be between 1 and 10"));

		final Response response = clienteResource.addTest(
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficinaExtension.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficinaExtension.json");
	}

	@Test
	public void updateClienteWithLongTelefonoOficinaExtension() {
		doThrow(new FieldNotValidException("telefonoOficinaExtension", "size must be between 1 and 10"))
				.when(clienteServices).testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficinaExtension.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficinaExtension.json");
	}

	@Test
	public void updateClienteWithShortTelefonoOficinaExtension() {
		doThrow(new FieldNotValidException("telefonoOficinaExtension", "size must be between 1 and 10"))
				.when(clienteServices).testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficinaExtension.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficinaExtension.json");
	}

	@Test
	public void addClienteWithShortTelefonoConyuge() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoConyuge", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoConyuge.json");
	}

	@Test
	public void addClienteWithLongTelefonoConyuge() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("telefonoConyuge", "size must be between 8 and 25"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoConyuge.json");
	}

	@Test
	public void addClienteWithLongFotografia() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("fotografia", "size must be between 2 and 500"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongFotografia.json");
	}

	@Test
	public void addClienteWithShortFotografia() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("fotografia", "size must be between 2 and 500"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortFotografia.json");
	}

	@Test
	public void addClienteWithLongReferidoPor() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("referidoPor", "size must be between 2 and 200"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongReferidoPor.json");
	}

	@Test
	public void addClienteWithShortReferidoPor() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("referidoPor", "size must be between 2 and 200"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortReferidoPor.json");
	}

	@Test
	public void addClienteWithInvalidFechaNacimiento() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void addClienteWithInvalidFechaAniversario() {

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaAniversario.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void addClienteWithInvalidFechaClientePreferido() {

		final Response response = clienteResource.addTest(
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaClientePreferido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void addClienteWithFutureFechaNacimiento() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("fechaNacimiento", "must be in the past"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaNacimiento.json");
	}

	@Test
	public void addClienteWithFutureFechaAniversario() {
		when(clienteServices.testAddCliente())
				.thenThrow(new FieldNotValidException("fechaAniversario", "must be in the past"));

		final Response response = clienteResource
				.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaAniversario.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaAniversario.json");
	}

	@Test
	public void updateValidClient() {

		final Response response = clienteResource.update(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "cliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertThat(response.getEntity().toString(), is(equalTo("")));

		//This is not working with composed entites, use it only with basic ones.
		//verify(clienteServices).update(clienteWithId(cliente, 1L));

	}

	@Test
	public void updateClientWithDataBelongingToOtherClient() {
		doThrow(new ClienteExistentException()).when(clienteServices).testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "cliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}

	@Test
	public void updateClienteWithNullPrimerNombre() {

		doThrow(new FieldNotValidException("primerNombre", "may not be null")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}

	@Test
	public void updateClienteWithNullPrimerApellido() {

		doThrow(new FieldNotValidException("primerApellido", "may not be null")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerApellido.json");
	}

	@Test
	public void updateClienteWithNullDireccion() {

		doThrow(new FieldNotValidException("direccion", "may not be null")).when(clienteServices).testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullDireccion.json");
	}

	@Test
	public void updateClienteWithNullHoraLocalizacion() {

		doThrow(new FieldNotValidException("horaLocalizacion", "may not be null")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullHoraLocalizacion.json");

	}

	@Test
	public void updateClienteWithInvalidColorCabello() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorCabello.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorCabello.json");
	}

	@Test
	public void updateClienteWithInvalidColorOjos() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorOjos.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorOjos.json");
	}

	@Test
	public void updateClienteWithInvalidFormaCara() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFormaCara.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFormaCara.json");
	}

	@Test
	public void updateClienteWithInvalidRangoEdad() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidRangoEdad.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidRangoEdad.json");

	}

	@Test
	public void updateClienteWithInvalidTipoLabios() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoLabios.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoLabios.json");
	}

	@Test
	public void updateClienteWithInvalidTipoPiel() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoPiel.json");
	}

	@Test
	public void updateClienteWithInvalidTonoBase() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoBase.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoBase.json");
	}

	@Test
	public void updateClienteWithInvalidTonoPiel() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoPiel.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoPiel.json");
	}

	@Test
	public void updateClienteWithLongPrimerNombre() {

		doThrow(new FieldNotValidException("primerNombre", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerNombre.json");
	}

	@Test
	public void updateClienteWithLongSegundoNombre() {

		doThrow(new FieldNotValidException("segundoNombre", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoNombre.json");
	}

	@Test
	public void updateClienteWithLongPrimerApellido() {

		doThrow(new FieldNotValidException("primerApellido", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerApellido.json");
	}

	@Test
	public void updateClienteWithLongSegundoApellido() {

		doThrow(new FieldNotValidException("segundoApellido", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoApellido.json");
	}

	@Test
	public void updateClienteWithLongApellidoCasada() {

		doThrow(new FieldNotValidException("apellidoCasada", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongApellidoCasada.json");
	}

	@Test
	public void updateClienteWithLongHoraLocalizacion() {

		doThrow(new FieldNotValidException("horaLocalizacion", "size must be between 2 and 20")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongHoraLocalizacion.json");
	}

	@Test
	public void updateClienteWithShortPrimerNombre() {

		doThrow(new FieldNotValidException("primerNombre", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerNombre.json");
	}

	@Test
	public void updateClienteWithShortSegundoNombre() {

		doThrow(new FieldNotValidException("segundoNombre", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoNombre.json");
	}

	@Test
	public void updateClienteWithShortPrimerApellido() {

		doThrow(new FieldNotValidException("primerApellido", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerApellido.json");

	}

	@Test
	public void updateClienteWithShortSegundoApellido() {

		doThrow(new FieldNotValidException("segundoApellido", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");

	}

	@Test
	public void updateClienteWithShortApellidoCasada() {

		doThrow(new FieldNotValidException("apellidoCasada", "size must be between 2 and 100")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}

	@Test
	public void updateClienteWithShortHoraLocalizacion() {

		doThrow(new FieldNotValidException("horaLocalizacion", "size must be between 2 and 20")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortHoraLocalizacion.json");
	}

	@Test
	public void updateClienteWithShortDireccion() {

		doThrow(new FieldNotValidException("direccion", "size must be between 2 and 1000")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortDireccion.json");

	}

	@Test
	public void updateClienteWithLongDireccion() {

		doThrow(new FieldNotValidException("direccion", "size must be between 2 and 1000")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongDireccion.json");

	}

	@Test
	public void updateClienteWithInvalidEmail() {

		doThrow(new FieldNotValidException("email", "not a well-formed email address")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidEmail.json");

	}

	@Test
	public void updateClienteWithLongEmail() {

		doThrow(new FieldNotValidException("email", "not a well-formed email address")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongEmail.json");
	}

	@Test
	public void updateClienteWithShortCelular() {

		doThrow(new FieldNotValidException("celular", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortCelular.json");
	}

	@Test
	public void updateClienteWithLongCelular() {

		doThrow(new FieldNotValidException("celular", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongCelular.json");

	}

	@Test
	public void updateClienteWithShortTelefonoCasa() {

		doThrow(new FieldNotValidException("telefonoCasa", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoCasa.json");

	}

	@Test
	public void updateClienteWithLongTelefonoCasa() {

		doThrow(new FieldNotValidException("telefonoCasa", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoCasa.json");

	}

	@Test
	public void updateClienteWithShortTelefonoOficina() {

		doThrow(new FieldNotValidException("telefonoOficina", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficina.json");

	}

	@Test
	public void updateClienteWithLongTelefonoOficina() {

		doThrow(new FieldNotValidException("telefonoOficina", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficina.json");

	}

	@Test
	public void updateClienteWithShortTelefonoConyuge() {

		doThrow(new FieldNotValidException("telefonoConyuge", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoConyuge.json");
	}

	@Test
	public void updateClienteWithLongTelefonoConyuge() {

		doThrow(new FieldNotValidException("telefonoConyuge", "size must be between 8 and 25")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoConyuge.json");
	}

	@Test
	public void updateClienteWithLongFotografia() {

		doThrow(new FieldNotValidException("fotografia", "size must be between 2 and 500")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongFotografia.json");

	}

	@Test
	public void updateClienteWithShortFotografia() {

		doThrow(new FieldNotValidException("fotografia", "size must be between 2 and 500")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortFotografia.json");

	}

	@Test
	public void updateClienteWithLongReferidoPor() {

		doThrow(new FieldNotValidException("referidoPor", "size must be between 2 and 200")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithLongReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongReferidoPor.json");

	}

	@Test
	public void updateClienteWithShortReferidoPor() {

		doThrow(new FieldNotValidException("referidoPor", "size must be between 2 and 200")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithShortReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortReferidoPor.json");

	}

	@Test
	public void updateClienteWithInvalidFechaNacimiento() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void updateClienteWithInvalidFechaAniversario() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void updateClienteWithInvalidFechaClientePreferido() {

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaClientePreferido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}

	@Test
	public void updateClienteWithFutureFechaNacimiento() {

		doThrow(new FieldNotValidException("fechaNacimiento", "must be in the past")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaNacimiento.json");
	}

	@Test
	public void updateClienteWithFutureFechaAniversario() {

		doThrow(new FieldNotValidException("fechaAniversario", "must be in the past")).when(clienteServices)
				.testUpdateCliente();

		final Response response = clienteResource.updateTest(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaAniversario.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaAniversario.json");
	}

	@Test
	public void updateClienteNotFound() {

		doThrow(new ClienteNotFoundException()).when(clienteServices).testUpdateCliente();

		final Response response = clienteResource.updateTest(2L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "cliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
		assertJsonResponseWithFile(response, "clienteNotFound.json");
	}

	@Test
	public void findCliente() {

		Cliente cliente = lucia();

		when(clienteServices.findById(1L)).thenReturn(clienteWithId(cliente, 1L));

		final Response response = clienteResource.findById(1L);
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertJsonResponseWithFile(response, "clienteFound.json");
	}

	@Test
	public void findClienteNotFound() {
		when(clienteServices.findById(1L)).thenThrow(new ClienteNotFoundException());

		final Response response = clienteResource.findById(1L);
		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
	}

	@Test
	public void findAllNoCliente() {
		when(clienteServices.findAll()).thenReturn(new ArrayList<>());

		final Response response = clienteResource.findAll();
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertJsonResponseWithFile(response, "emptyListOfClientes.json");
	}

	@Test
	public void findAllTwoClientes() {

		Cliente clienteLucia = lucia();
		Cliente clienteMaria = enieytilde();

		when(clienteServices.findAll())
				.thenReturn(Arrays.asList(clienteWithId(clienteLucia, 1L), clienteWithId(clienteMaria, 2L)));

		final Response response = clienteResource.findAll();
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertJsonResponseWithFile(response, "twoClientes.json");
	}

	@Test
	public void deleteCliente() {

		Cliente cliente = lucia();

		when(clienteServices.findById(1L)).thenReturn(clienteWithId(cliente, 1L));

		final Response response = clienteResource.delete(1L);

		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
	}

	@Test
	public void deleteClienteNotFound() {

		doThrow(new ClienteNotFoundException()).when(clienteServices).findById(1L);

		final Response response = clienteResource.delete(1L);

		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
	}

	private void assertJsonResponseWithFile(final Response response, final String fileName) {
		assertJsonMatchesFileContent(response.getEntity().toString(), getPathFileResponse(PATH_RESOURCE, fileName));
	}

}
