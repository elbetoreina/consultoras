package com.consultoras.app.cliente.repository;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.commontests.utils.DBCommandTransactionalExecutor;

public class ClienteRepositoryUTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private ClienteRepository clienteRepository;
	private DBCommandTransactionalExecutor dbCommandTransactionalExecutor;

	@Before
	public void initTestCase() {
		emf = Persistence.createEntityManagerFactory("consultorasPU");
		em = emf.createEntityManager();

		clienteRepository = new ClienteRepository();
		clienteRepository.em = em;

		dbCommandTransactionalExecutor = new DBCommandTransactionalExecutor(em);
	}

	@After
	public void closeEntityManager() {
		em.close();
		emf.close();
	}

	@Test
	public void addClienteAndFindIt() {
		final Long clienteAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return clienteRepository.add(lucia()).getId();
		});

		assertThat(clienteAddedId, is(notNullValue()));

		final Cliente cliente = clienteRepository.findById(clienteAddedId);

		assertThat(cliente, is(notNullValue()));

		CompareClientes(cliente, lucia());

	}

	@Test
	public void findClienteByIdNotFound() {
		final Cliente cliente = clienteRepository.findById(999L);
		assertThat(cliente, is(nullValue()));
	}

	@Test
	public void findClienteByIdWithNullId() {
		final Cliente cliente = clienteRepository.findById(null);
		assertThat(cliente, is(nullValue()));
	}

	@Test
	public void updateCliente() {
		final Long clienteAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return clienteRepository.add(lucia()).getId();
		});
		
		assertThat(clienteAddedId, is(notNullValue()));

		final Cliente clienteAfterAdd = clienteRepository.findById(clienteAddedId);
		
		CompareClientes(clienteAfterAdd, lucia());		
		
		clienteAfterAdd.setConsultoraId(maria().getConsultoraId());
		clienteAfterAdd.setPrimerNombre(maria().getPrimerNombre());
		clienteAfterAdd.setSegundoNombre(maria().getSegundoNombre());
		clienteAfterAdd.setPrimerApellido(maria().getPrimerApellido());
		clienteAfterAdd.setSegundoApellido(maria().getSegundoApellido());
		clienteAfterAdd.setApellidoCasada(maria().getApellidoCasada());
		clienteAfterAdd.setFechaNacimiento(maria().getFechaNacimiento());
		clienteAfterAdd.setFechaAniversario(maria().getFechaAniversario());
		clienteAfterAdd.setHoraLocalizacion(maria().getHoraLocalizacion());
		clienteAfterAdd.setDireccion(maria().getDireccion());
		clienteAfterAdd.setEmail(maria().getEmail());
		clienteAfterAdd.setCelular(maria().getCelular());
		clienteAfterAdd.setTelefonoCasa(maria().getTelefonoCasa());
		clienteAfterAdd.setTelefonoOficina(maria().getTelefonoOficina());
		clienteAfterAdd.setTelefonoOficina(maria().getTelefonoOficina());
		clienteAfterAdd.setTelefonoOficinaExtension(maria().getTelefonoOficinaExtension());
		clienteAfterAdd.setTelefonoConyuge(maria().getTelefonoConyuge());
		clienteAfterAdd.setFotografia(maria().getFotografia());
		clienteAfterAdd.setRangoEdad(maria().getRangoEdad());
		clienteAfterAdd.setTonoBase(maria().getTonoBase());
		clienteAfterAdd.setTipoLabios(maria().getTipoLabios());
		clienteAfterAdd.setFormaCara(maria().getFormaCara());
		clienteAfterAdd.setTipoPiel(maria().getTipoPiel());
		clienteAfterAdd.setTonoPiel(maria().getTonoPiel());
		clienteAfterAdd.setColorCabello(maria().getColorCabello());
		clienteAfterAdd.setColorOjos(maria().getColorOjos());
		clienteAfterAdd.setFechaClientePreferido(maria().getFechaClientePreferido());
		clienteAfterAdd.setReferidoPor(maria().getReferidoPor());
		
		dbCommandTransactionalExecutor.executeCommand(() -> {
			clienteRepository.update(clienteAfterAdd);
			return null;
		});
		
		final Cliente categoryAfterUpdate = clienteRepository.findById(clienteAddedId);
		
		CompareClientes(categoryAfterUpdate, maria());

	}
	
	@Test
	public void deleteCliente(){
		
		final Long clienteAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return clienteRepository.add(lucia()).getId();
		});
		
		assertThat(clienteAddedId, is(notNullValue()));

		final Cliente clienteAfterAdd = clienteRepository.findById(clienteAddedId);
		
		dbCommandTransactionalExecutor.executeCommand(() -> {
			clienteRepository.delete(clienteAfterAdd);
			return null;
		});
		
		final Cliente clienteAfterDelete = clienteRepository.findById(clienteAddedId);
		
		assertThat(clienteAfterDelete, is(nullValue()));
		
	}
	
	@Test
	public void findAllClientes(){
		dbCommandTransactionalExecutor.executeCommand(() -> {
			allClientes().forEach(clienteRepository::add);
			return null;
		});
		
		final List<Cliente> clientes = clienteRepository.findAll("primerNombre");
		
		assertThat(clientes.size(), is(equalTo(3)));
		assertThat(clientes.get(0).getPrimerNombre(), is(equalTo(enieytilde().getPrimerNombre())));
		assertThat(clientes.get(1).getPrimerNombre(), is(equalTo(lucia().getPrimerNombre())));
		assertThat(clientes.get(2).getPrimerNombre(), is(equalTo(maria().getPrimerNombre())));
		
	}
	
	
	@Test
	public void alreadyExistsForAdd() {
		dbCommandTransactionalExecutor.executeCommand(() -> {
			clienteRepository.add(lucia());
			return null;
		});

		assertThat(clienteRepository.alreadyExists(lucia()), is(equalTo(true)));
		assertThat(clienteRepository.alreadyExists(maria()), is(equalTo(false)));
	}
	
	@Test
	public void alreadyExistsClientWithId() {
		final Cliente lucia = dbCommandTransactionalExecutor.executeCommand(() -> {
			clienteRepository.add(maria());
			return clienteRepository.add(lucia());
		});

		assertThat(clienteRepository.alreadyExists(lucia), is(equalTo(false)));

		lucia.setPrimerNombre(maria().getPrimerNombre());
		lucia.setPrimerApellido(maria().getPrimerApellido());
		lucia.setCelular(maria().getCelular());
		
		assertThat(clienteRepository.alreadyExists(lucia), is(equalTo(true)));

		lucia.setPrimerNombre(enieytilde().getPrimerNombre());
		lucia.setPrimerApellido(enieytilde().getPrimerApellido());
		lucia.setCelular(maria().getCelular());
		
		assertThat(clienteRepository.alreadyExists(lucia), is(equalTo(false)));
	}
	
	@Test
	public void existsById() {
		final Long clienteAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return clienteRepository.add(lucia()).getId();
		});

		assertThat(clienteRepository.existsById(clienteAddedId), is(equalTo(true)));
		assertThat(clienteRepository.existsById(999L), is(equalTo(false)));
	}

	@SuppressWarnings("static-access")
	public void CompareClientes(Cliente clienteOrigen, Cliente clienteDestino) {
		assertThat(clienteOrigen, is(notNullValue()));

		assertThat(clienteOrigen.getConsultoraId(), is(equalTo(clienteDestino.getConsultoraId())));
		assertThat(clienteOrigen.getPrimerNombre(), is(equalTo(clienteDestino.getPrimerNombre())));
		assertThat(clienteOrigen.getSegundoNombre(), is(equalTo(clienteDestino.getSegundoNombre())));
		assertThat(clienteOrigen.getPrimerApellido(), is(equalTo(clienteDestino.getPrimerApellido())));
		assertThat(clienteOrigen.getSegundoApellido(), is(equalTo(clienteDestino.getSegundoApellido())));
		assertThat(clienteOrigen.getApellidoCasada(), is(equalTo(clienteDestino.getApellidoCasada())));
		assertThat(clienteOrigen.getFechaNacimiento().YEAR, is(equalTo(clienteDestino.getFechaNacimiento().YEAR)));
		assertThat(clienteOrigen.getFechaNacimiento().MONTH, is(equalTo(clienteDestino.getFechaNacimiento().MONTH)));
		assertThat(clienteOrigen.getFechaNacimiento().DAY_OF_MONTH,
				is(equalTo(clienteDestino.getFechaNacimiento().DAY_OF_MONTH)));
		assertThat(clienteOrigen.getFechaAniversario().YEAR, is(equalTo(clienteDestino.getFechaAniversario().YEAR)));
		assertThat(clienteOrigen.getFechaAniversario().MONTH, is(equalTo(clienteDestino.getFechaAniversario().MONTH)));
		assertThat(clienteOrigen.getFechaAniversario().DAY_OF_MONTH,
				is(equalTo(clienteDestino.getFechaAniversario().DAY_OF_MONTH)));
		assertThat(clienteOrigen.getHoraLocalizacion(), is(equalTo(clienteDestino.getHoraLocalizacion())));
		assertThat(clienteOrigen.getDireccion(), is(equalTo(clienteDestino.getDireccion())));
		assertThat(clienteOrigen.getEmail(), is(equalTo(clienteDestino.getEmail())));
		assertThat(clienteOrigen.getCelular(), is(equalTo(clienteDestino.getCelular())));
		assertThat(clienteOrigen.getTelefonoCasa(), is(equalTo(clienteDestino.getTelefonoCasa())));
		assertThat(clienteOrigen.getTelefonoOficina(), is(equalTo(clienteDestino.getTelefonoOficina())));
		assertThat(clienteOrigen.getTelefonoOficinaExtension(), is(equalTo(clienteDestino.getTelefonoOficinaExtension())));
		assertThat(clienteOrigen.getTelefonoConyuge(), is(equalTo(clienteDestino.getTelefonoConyuge())));
		assertThat(clienteOrigen.getFotografia(), is(equalTo(clienteDestino.getFotografia())));
		assertThat(clienteOrigen.getRangoEdad(), is(equalTo(clienteDestino.getRangoEdad())));
		assertThat(clienteOrigen.getTonoBase(), is(equalTo(clienteDestino.getTonoBase())));
		assertThat(clienteOrigen.getTipoLabios(), is(equalTo(clienteDestino.getTipoLabios())));
		assertThat(clienteOrigen.getFormaCara(), is(equalTo(clienteDestino.getFormaCara())));
		assertThat(clienteOrigen.getTipoPiel(), is(equalTo(clienteDestino.getTipoPiel())));
		assertThat(clienteOrigen.getTonoPiel(), is(equalTo(clienteDestino.getTonoPiel())));
		assertThat(clienteOrigen.getColorCabello(), is(equalTo(clienteDestino.getColorCabello())));
		assertThat(clienteOrigen.getColorOjos(), is(equalTo(clienteDestino.getColorOjos())));
		assertThat(clienteOrigen.getFechaClientePreferido().YEAR,
				is(equalTo(clienteDestino.getFechaClientePreferido().YEAR)));
		assertThat(clienteOrigen.getFechaClientePreferido().MONTH,
				is(equalTo(clienteDestino.getFechaClientePreferido().MONTH)));
		assertThat(clienteOrigen.getFechaClientePreferido().DAY_OF_MONTH,
				is(equalTo(clienteDestino.getFechaClientePreferido().DAY_OF_MONTH)));
		assertThat(clienteOrigen.getReferidoPor(), is(equalTo(clienteDestino.getReferidoPor())));

	}

}
