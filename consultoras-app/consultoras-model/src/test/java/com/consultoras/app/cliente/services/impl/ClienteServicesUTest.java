package com.consultoras.app.cliente.services.impl;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

import com.consultoras.app.cliente.exception.ClienteExistentException;
import com.consultoras.app.cliente.exception.ClienteNotFoundException;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.repository.ClienteRepository;
import com.consultoras.app.cliente.services.ClienteServices;
import com.consultoras.app.cliente.services.impl.ClientServicesImpl;
import com.consultoras.app.common.exception.FieldNotValidException;

public class ClienteServicesUTest {

	private ClienteServices clienteServices;
	private ClienteRepository clienteRepository;
	private Validator validator;

	private static final String longName100 = "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij";

	private static final String longName1000 = "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij abcdefghij "
			+ "abcdefghij abcdefghij abcdefghij abcdefghij";

	@Before
	public void initTestCase() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		clienteRepository = mock(ClienteRepository.class);

		clienteServices = new ClientServicesImpl();
		((ClientServicesImpl) clienteServices).validator = validator;
		((ClientServicesImpl) clienteServices).clienteRepository = clienteRepository;
	}

	@Test
	public void addClienteWithNullConsultora() {

		Cliente cliente = new Cliente();

		cliente = lucia();

		cliente.setConsultora(null);

		try {
			clienteServices.add(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("consultora")));
		}

	}

	@Test
	public void addClienteWithNullPrimerNombre() {
		addClienteWithInvalidStringField(null, "primerNombre");
	}

	@Test
	public void addClienteWithNullPrimerApellido() {
		addClienteWithInvalidStringField(null, "primerApellido");
	}

	@Test
	public void addClienteWithNullHoraLocalizacion() {
		addClienteWithInvalidStringField(null, "horaLocalizacion");
	}

	@Test
	public void addClienteWithNullDireccion() {
		addClienteWithInvalidStringField(null, "direccion");
	}

	@Test
	public void addClienteWithShortPrimerNombre() {
		addClienteWithInvalidStringField("A", "primerNombre");
	}

	@Test
	public void addClienteWithShortSegundoNombre() {
		addClienteWithInvalidStringField("A", "segundoNombre");
	}

	@Test
	public void addClienteWithShortPrimerApellido() {
		addClienteWithInvalidStringField("A", "primerApellido");
	}

	@Test
	public void addClienteWithShortSegundoApellido() {
		addClienteWithInvalidStringField("A", "segundoApellido");
	}

	@Test
	public void addClienteWithShortApellidoCasada() {
		addClienteWithInvalidStringField("A", "apellidoCasada");
	}

	@Test
	public void addClienteWithShortHoraLocalizacion() {
		addClienteWithInvalidStringField("A", "horaLocalizacion");
	}

	@Test
	public void addClienteWithShortDireccion() {
		addClienteWithInvalidStringField("A", "direccion");
	}

	@Test
	public void addClienteWithShortEmail() {
		addClienteWithInvalidStringField("A", "email");
	}

	@Test
	public void addClientWithShortCelular() {
		addClienteWithInvalidStringField("1", "celular");
	}

	@Test
	public void addClientWithShortTelefonoCasa() {
		addClienteWithInvalidStringField("1", "telefonoCasa");
	}

	@Test
	public void addClientWithShortTelefonoConyuge() {
		addClienteWithInvalidStringField("1", "telefonoConyuge");
	}

	@Test
	public void addClientWithShortTelefonoOficina() {
		addClienteWithInvalidStringField("1", "telefonoOficina");
	}

	@Test
	public void addClientWithShortFotografia() {
		addClienteWithInvalidStringField("A", "fotografia");
	}

	@Test
	public void addClientWithShortReferidoPor() {
		addClienteWithInvalidStringField("A", "referidoPor");
	}

	@Test
	public void addClienteWithLongPrimerNombre() {
		addClienteWithInvalidStringField(longName100, "primerNombre");
	}

	@Test
	public void addClienteWithLongSegundoNombre() {
		addClienteWithInvalidStringField(longName100, "segundoNombre");
	}

	@Test
	public void addClienteWithLongPrimerApellido() {
		addClienteWithInvalidStringField(longName100, "primerApellido");
	}

	@Test
	public void addClienteWithLongSegundoApellido() {
		addClienteWithInvalidStringField(longName100, "segundoApellido");
	}

	@Test
	public void addClienteWithLongApellidoCasada() {
		addClienteWithInvalidStringField(longName100, "apellidoCasada");
	}

	@Test
	public void addClienteWithLongHoraLocalizacion() {
		addClienteWithInvalidStringField(longName100, "horaLocalizacion");
	}

	@Test
	public void addClienteWithLongDireccion() {
		addClienteWithInvalidStringField(longName1000, "direccion");
	}

	@Test
	public void addClienteWithLongEmail() {
		addClienteWithInvalidStringField(longName1000, "email");
	}

	@Test
	public void addClientWithLongCelular() {
		addClienteWithInvalidStringField(longName100, "celular");
	}

	@Test
	public void addClientWithLongTelefonoCasa() {
		addClienteWithInvalidStringField(longName100, "telefonoCasa");
	}

	@Test
	public void addClientWithLongTelefonoConyuge() {
		addClienteWithInvalidStringField(longName100, "telefonoConyuge");
	}

	@Test
	public void addClientWithLongTelefonoOficina() {
		addClienteWithInvalidStringField(longName100, "telefonoOficina");
	}

	@Test
	public void addClientWithLongTelefonoOficinaExtension() {
		addClienteWithInvalidStringField("12345678901", "telefonoOficinaExtension");
	}

	@Test
	public void addClientWithLongFotografia() {
		addClienteWithInvalidStringField(longName1000, "fotografia");
	}

	@Test
	public void addClientWithLongReferidoPor() {
		addClienteWithInvalidStringField(longName1000, "referidoPor");
	}

	@Test
	public void addClienteWithIncorrectEmail() {
		addClienteWithInvalidStringField("esto no es un email", "email");
	}

	@Test
	public void addClienteWithNullFechaNacimiento() {

		try {
			Cliente cliente = new Cliente();

			cliente = lucia();

			cliente.setFechaNacimiento(null);

			clienteServices.add(cliente);
			fail("An error should have been thrown");

		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("fechaNacimiento")));
		}

	}

	@Test
	public void AddClienteWithFutureFechaNacimiento() {

		Cliente cliente = new Cliente();

		cliente = lucia();

		try {
			Calendar fechaNacimientoFutura = Calendar.getInstance();

			fechaNacimientoFutura.setTime(new Date());

			fechaNacimientoFutura.add(Calendar.DATE, 1);

			cliente.setFechaNacimiento(fechaNacimientoFutura);

			clienteServices.add(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("fechaNacimiento")));
		}

	}

	@Test
	public void AddClienteWithFutureFechaAniversario() {

		Cliente cliente = new Cliente();

		cliente = lucia();

		try {
			Calendar fechaAniversarioFutura = Calendar.getInstance();

			fechaAniversarioFutura.setTime(new Date());

			fechaAniversarioFutura.add(Calendar.DATE, 1);

			cliente.setFechaAniversario(fechaAniversarioFutura);

			clienteServices.add(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("fechaAniversario")));
		}

	}

	@Test(expected = ClienteExistentException.class)
	public void addClienteWithExistentName() {

		Cliente cliente = lucia();

		when(clienteRepository.alreadyExists(cliente)).thenReturn(true);

		clienteServices.add(cliente);
	}

	@Test
	public void addValidCliente() {

		Cliente cliente = lucia();

		when(clienteRepository.alreadyExists(cliente)).thenReturn(false);

		when(clienteRepository.add(cliente)).thenReturn(clienteWithId(cliente, 1L));

		final Cliente clienteAdded = clienteServices.add(cliente);
		assertThat(clienteAdded.getId(), is(equalTo(1L)));
	}

	@Test
	public void updateClienteWithNullConsultoraId() {
		Cliente cliente = new Cliente();

		cliente = lucia();

		cliente.setConsultora(null);

		try {
			clienteServices.update(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("consultora")));
		}
	}
	
	@Test
	public void updateClienteWithNullPrimerNombre() {
		updateClienteWithInvalidStringField(null, "primerNombre");
	}

	@Test
	public void updateClienteWithNullPrimerApellido() {
		updateClienteWithInvalidStringField(null, "primerApellido");
	}

	@Test
	public void updateClienteWithNullHoraLocalizacion() {
		updateClienteWithInvalidStringField(null, "horaLocalizacion");
	}

	@Test
	public void updateClienteWithNullDireccion() {
		updateClienteWithInvalidStringField(null, "direccion");
	}

	@Test
	public void updateClienteWithShortPrimerNombre() {
		updateClienteWithInvalidStringField("A", "primerNombre");
	}

	@Test
	public void updateClienteWithShortSegundoNombre() {
		updateClienteWithInvalidStringField("A", "segundoNombre");
	}

	@Test
	public void updateClienteWithShortPrimerApellido() {
		updateClienteWithInvalidStringField("A", "primerApellido");
	}

	@Test
	public void updateClienteWithShortSegundoApellido() {
		updateClienteWithInvalidStringField("A", "segundoApellido");
	}

	@Test
	public void udpateClienteWithShortApellidoCasada() {
		updateClienteWithInvalidStringField("A", "apellidoCasada");
	}

	@Test
	public void updateClienteWithShortHoraLocalizacion() {
		updateClienteWithInvalidStringField("A", "horaLocalizacion");
	}

	@Test
	public void updateClienteWithShortDireccion() {
		updateClienteWithInvalidStringField("A", "direccion");
	}

	@Test
	public void updateClienteWithShortEmail() {
		updateClienteWithInvalidStringField("A", "email");
	}

	@Test
	public void updateClienteWithShortCelular() {
		updateClienteWithInvalidStringField("1", "celular");
	}

	@Test
	public void updateClienteWithShortTelefonoCasa() {
		updateClienteWithInvalidStringField("1", "telefonoCasa");
	}

	@Test
	public void updateClienteWithShortTelefonoConyuge() {
		updateClienteWithInvalidStringField("1", "telefonoConyuge");
	}

	@Test
	public void updateClienteWithShortFotografia() {
		updateClienteWithInvalidStringField("A", "fotografia");
	}

	@Test
	public void updateClienteWithShortReferidoPor() {
		updateClienteWithInvalidStringField("A", "referidoPor");
	}

	@Test(expected = ClienteExistentException.class)
	public void updateClienteWithAlreadyExistentValues() {

		Cliente cliente = lucia();

		when(clienteRepository.alreadyExists(clienteWithId(cliente, 1L))).thenReturn(true);

		clienteServices.update(clienteWithId(cliente, 1L));
	}

	@Test(expected = ClienteNotFoundException.class)
	public void updateClienteNotFound() {

		Cliente cliente = lucia();

		when(clienteRepository.alreadyExists(clienteWithId(cliente, 1L))).thenReturn(false);
		when(clienteRepository.existsById(1L)).thenReturn(false);

		clienteServices.update(clienteWithId(cliente, 1L));
	}

	@Test
	public void updateValidCliente() {

		Cliente cliente = lucia();

		when(clienteRepository.alreadyExists(clienteWithId(cliente, 1L))).thenReturn(false);

		when(clienteRepository.existsById(1L)).thenReturn(true);

		clienteServices.update(clienteWithId(cliente, 1L));

		verify(clienteRepository).update(clienteWithId(cliente, 1L));
	}

	@Test
	public void findClienteById() {

		Cliente cliente = lucia();

		when(clienteRepository.findById(1L)).thenReturn(clienteWithId(cliente, 1L));

		final Cliente clienteFound = clienteServices.findById(1L);

		assertThat(clienteFound, is(notNullValue()));
		assertThat(clienteFound.getId(), is(equalTo(1L)));
		assertThat(clienteFound.getPrimerApellido(), is(equalTo(lucia().getPrimerApellido())));
		assertThat(clienteFound.getPrimerNombre(), is(equalTo(lucia().getPrimerNombre())));
		assertThat(clienteFound.getCelular(), is(equalTo(lucia().getCelular())));
	}

	@Test
	public void findAllClientes() {
		when(clienteRepository.findAll("primerApellido"))
				.thenReturn(Arrays.asList(clienteWithId(lucia(), 1L), clienteWithId(maria(), 2L)));

		final List<Cliente> clientes = clienteServices.findAll();
		assertThat(clientes.size(), is(equalTo(2)));
		assertThat(clientes.get(0).getPrimerNombre(), is(equalTo(lucia().getPrimerNombre())));
		assertThat(clientes.get(1).getPrimerNombre(), is(equalTo(maria().getPrimerNombre())));
	}

	@Test(expected = ClienteNotFoundException.class)
	public void findClienteByIdNotFound() {
		when(clienteRepository.findById(1L)).thenReturn(null);

		clienteServices.findById(1L);
	}

	@Test
	public void findAllNoClientes() {
		when(clienteRepository.findAll("primerApellido")).thenReturn(new ArrayList<>());

		final List<Cliente> clientes = clienteServices.findAll();
		assertThat(clientes.isEmpty(), is(equalTo(true)));
	}

	@Test(expected = ClienteNotFoundException.class)
	public void deleteClienteNotFound() {

		Cliente cliente = lucia();

		when(clienteRepository.existsById(1L)).thenReturn(false);

		clienteServices.delete(clienteWithId(cliente, 1L));

	}

	@Test
	public void deleteValidCliente() {

		Cliente cliente = lucia();

		when(clienteRepository.existsById(1L)).thenReturn(true);

		clienteServices.delete(clienteWithId(cliente, 1L));

	}

	private void addClienteWithInvalidStringField(final String fieldValue, final String fieldName) {

		Cliente cliente = new Cliente();

		cliente = lucia();

		try {
			switch (fieldName) {
			case "primerNombre":
				cliente.setPrimerNombre(fieldValue);
				break;
			case "segundoNombre":
				cliente.setSegundoNombre(fieldValue);
				break;
			case "primerApellido":
				cliente.setPrimerApellido(fieldValue);
				break;
			case "segundoApellido":
				cliente.setSegundoApellido(fieldValue);
				break;
			case "apellidoCasada":
				cliente.setApellidoCasada(fieldValue);
				break;
			case "horaLocalizacion":
				cliente.setHoraLocalizacion(fieldValue);
				break;
			case "direccion":
				cliente.setDireccion(fieldValue);
				break;
			case "email":
				cliente.setEmail(fieldValue);
				break;
			case "celular":
				cliente.setCelular(fieldValue);
				break;
			case "telefonoCasa":
				cliente.setTelefonoCasa(fieldValue);
				break;
			case "telefonoOficina":
				cliente.setTelefonoOficina(fieldValue);
				break;
			case "telefonoOficinaExtension":
				cliente.setTelefonoOficinaExtension(fieldValue);
				break;
			case "telefonoConyuge":
				cliente.setTelefonoConyuge(fieldValue);
				break;
			case "fotografia":
				cliente.setFotografia(fieldValue);
				break;
			case "referidoPor":
				cliente.setReferidoPor(fieldValue);
				break;

			default:
				break;

			}
			clienteServices.add(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo(fieldName)));
		}
	}

	private void updateClienteWithInvalidStringField(final String fieldValue, final String fieldName) {

		Cliente cliente = new Cliente();

		cliente = lucia();

		try {
			switch (fieldName) {
			case "primerNombre":
				cliente.setPrimerNombre(fieldValue);
				break;
			case "segundoNombre":
				cliente.setSegundoNombre(fieldValue);
				break;
			case "primerApellido":
				cliente.setPrimerApellido(fieldValue);
				break;
			case "segundoApellido":
				cliente.setSegundoApellido(fieldValue);
				break;
			case "apellidoCasada":
				cliente.setApellidoCasada(fieldValue);
				break;
			case "horaLocalizacion":
				cliente.setHoraLocalizacion(fieldValue);
				break;
			case "direccion":
				cliente.setDireccion(fieldValue);
				break;
			case "email":
				cliente.setEmail(fieldValue);
				break;
			case "celular":
				cliente.setCelular(fieldValue);
				break;
			case "telefonoCasa":
				cliente.setTelefonoCasa(fieldValue);
				break;
			case "telefonoOficina":
				cliente.setTelefonoOficina(fieldValue);
				break;
			case "telefonoOficinaExtension":
				cliente.setTelefonoOficinaExtension(fieldValue);
				break;
			case "telefonoConyuge":
				cliente.setTelefonoConyuge(fieldValue);
				break;
			case "fotografia":
				cliente.setFotografia(fieldValue);
				break;
			case "referidoPor":
				cliente.setReferidoPor(fieldValue);
				break;

			default:
				break;

			}
			clienteServices.update(cliente);
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo(fieldName)));
		}
	}
}
