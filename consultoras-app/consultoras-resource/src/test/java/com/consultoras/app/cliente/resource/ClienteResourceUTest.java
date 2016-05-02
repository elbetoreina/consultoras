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
	public void addClienteWithLongSegundoApellido() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("segundoApellido", "El Segundo Apellido del Cliente es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongSegundoApellido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoApellido.json");
	}
	
	@Test
	public void addClienteWithLongApellidoCasada() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("apellidoCasada", "El Apellido de Casada del Cliente es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongApellidoCasada.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongApellidoCasada.json");
	}
	
	@Test
	public void addClienteWithLongHoraLocalizacion() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("horaLocalizacion", "El valor de la hora de localizacion es demasiado largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongHoraLocalizacion.json");
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
	
	@Test
	public void addClienteWithShortHoraLocalizacion() {

		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("horaLocalizacion", "El valor de la hora de localizacion es demasiado corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortHoraLocalizacion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortHoraLocalizacion.json");
	}
	
	@Test
	public void addClienteWithShortDireccion() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("direccion", "La Direccion del cliente es demasiado corta"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortDireccion.json");
	}
	
	@Test
	public void addClienteWithLongDireccion() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("direccion", "La Direccion del cliente es demasiado larga"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongDireccion.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongDireccion.json");
	}
	
	@Test
	public void addClienteWithInvalidEmail() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("email", "La direccion de correo del cliente es invalida"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidEmail.json");
	}
	
	@Test
	public void addClienteWithLongEmail() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("email", "La direccion de correo del cliente es muy larga"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongEmail.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongEmail.json");
	}
	
	@Test
	public void addClienteWithShortCelular() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("celular", "El numero de telefono celular es muy corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortCelular.json");
	}
	
	@Test
	public void addClienteWithLongCelular() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("celular", "El numero de telefono celular es muy largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongCelular.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongCelular.json");
	}
	
	@Test
	public void addClienteWithShortTelefonoCasa() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoCasa", "El numero de telefono de casa es muy corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoCasa.json");
	}
	
	@Test
	public void addClienteWithLongTelefonoCasa() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoCasa", "El numero de telefono de casa es muy largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongTelefonoCasa.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoCasa.json");
	}
	
	
	@Test
	public void addClienteWithShortTelefonoOficina() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoOficina", "El numero de telefono de oficina es muy corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficina.json");
	}
	
	@Test
	public void addClienteWithLongTelefonoOficina() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoOficina", "El numero de telefono de oficina es muy largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongTelefonoOficina.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficina.json");
	}
	
	
	@Test
	public void addClienteWithShortTelefonoConyuge() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoConyuge", "El numero de telefono de conyuge es muy corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoConyuge.json");
	}
	
	@Test
	public void addClienteWithLongTelefonoConyuge() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("telefonoConyuge", "El numero de telefono de conyuge es muy largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongTelefonoConyuge.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoConyuge.json");
	}
	
	@Test
	public void addClienteWithLongFotografia() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("fotografia", "La ubicacion de el archivo de fotografia es muy larga"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongFotografia.json");
	}
	
	
	@Test
	public void addClienteWithShortFotografia() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("fotografia", "La ubicacion de el archivo de fotografia es muy corta"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortFotografia.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortFotografia.json");
	}
	
	
	@Test
	public void addClienteWithLongReferidoPor() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("referidoPor", "El nombre de persona que refiere es muy largo"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithLongReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongReferidoPor.json");
	}
	
	
	@Test
	public void addClienteWithShortReferidoPor() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("referidoPor", "El nombre de persona que refiere es muy corto"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithShortReferidoPor.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortReferidoPor.json");
	}	
	
	@Test
	public void addClienteWithInvalidFechaNacimiento() {		

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	
	
	@Test
	public void addClienteWithInvalidFechaAniversario() {		

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidFechaAniversario.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	
	@Test
	public void addClienteWithInvalidFechaClientePreferido() {		

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithInvalidFechaClientePreferido.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	public void addClienteWithFutureFechaNacimiento() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("fechaNacimiento", "La fecha de nacimiento no puede estar en el futuro"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithFutureFechaNacimiento.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaNacimiento.json");
	}
	
	@Test
	public void addClienteWithFutureFechaAniversario() {
		when(clienteServices.testAddCliente()).thenThrow(new FieldNotValidException("fechaAniversario", "La fecha de aniversario no puede estar en el futuro"));

		final Response response = clienteResource.addTest(readJsonFile(getPathFileRequest(PATH_RESOURCE,
				"clienteWithFutureFechaAniversario.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaAniversario.json");
	}
	
	@Test
	public void updateValidClient() {
		
		Cliente cliente = lucia();		
		
		final Response response = clienteResource.update(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "cliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertThat(response.getEntity().toString(), is(equalTo("")));

		verify(clienteServices).update(clienteWithId(cliente, 1L));
		
	}
	
	@Test
	public void updateClientWithDataBelongingToOtherClient() {
		
		Cliente cliente = lucia();
		
		doThrow(new ClienteExistentException()).when(clienteServices).update(clienteWithId(cliente, 1L));

		final Response response = clienteResource.update(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "cliente.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}
	
	@Test
	public void updateClienteWithNullPrimerNombre() {
		
		Cliente cliente = lucia();
		
		doThrow(new FieldNotValidException("primerNombre", "El Primer Nombre del cliente aparece vacio o nulo")).when(clienteServices).update(
				clienteWithId(cliente, 1L));

		final Response response = clienteResource.update(1L,
				readJsonFile(getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerNombre.json")));
		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}
	
	
	
	
	
	private void assertJsonResponseWithFile(final Response response, final String fileName) {
		assertJsonMatchesFileContent(response.getEntity().toString(), getPathFileResponse(PATH_RESOURCE, fileName));
	}
	
	

}
