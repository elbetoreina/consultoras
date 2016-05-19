package com.consultoras.app.cliente.resource;

import static com.consultoras.app.commontests.cliente.ClienteForTestsRepository.*;
import static com.consultoras.app.commontests.utils.FileTestNameUtils.*;
import static com.consultoras.app.commontests.utils.JsonTestUtils.*;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.net.URL;

import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.common.json.JsonReader;
import com.consultoras.app.common.model.HttpCode;
import com.consultoras.app.commontests.utils.IntTestUtils;
import com.consultoras.app.commontests.utils.ResourceClient;
import com.consultoras.app.commontests.utils.ResourceDefinitions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RunWith(Arquillian.class)
public class ClienteResourceIntTest {

	@ArquillianResource
	private URL url;

	private ResourceClient resourceClient;

	private static final String PATH_RESOURCE = ResourceDefinitions.CLIENTE.getResourceName();

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addPackages(true, "com.consultoras.app")
				.addAsResource("persistence-integration.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").setWebXML(new File("src/test/resources/web.xml"))
				.addAsLibraries(
						Maven.resolver().resolve("com.google.code.gson:gson:2.3.1", "org.mockito:mockito-core:1.9.5")
								.withoutTransitivity().asFile());

	}

	@Before
	public void initTestCase() {
		this.resourceClient = new ResourceClient(url);
		
		resourceClient.resourcePath("/DB").deleteAll();

	}

	@Test
	@RunAsClient
	public void addValidClienteAndFindIt() {

		final Long id = addClienteAndGetId("cliente.json");
		findClienteAndAssertResponseWithCliente(id, lucia());		

	}
	
	@Test
	@RunAsClient
	public void addClienteWithNullPrimerNombre() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithNullPrimerApellido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithNullDireccion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullDireccion.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithNullHoraLocalizacion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidColorOjos() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorOjos.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorOjos.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidFormaCara() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFormaCara.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFormaCara.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidRangoEdad() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidRangoEdad.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidRangoEdad.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidTipoLabios() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoLabios.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoLabios.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidTipoPiel() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoPiel.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoPiel.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidTonoBase() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoBase.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoBase.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidTonoPiel() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoPiel.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoPiel.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidColorCabello() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorCabello.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorCabello.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongPrimerNombre() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongSegundoNombre() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoNombre.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongPrimerApellido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongSegundoApellido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoApellido.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongApellidoCasada() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongApellidoCasada.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongApellidoCasada.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongHoraLocalizacion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongDireccion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongDireccion.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongEmail() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongEmail.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongEmail.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongCelular() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongCelular.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongCelular.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongTelefonoCasa() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoCasa.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoCasa.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongTelefonoOficina() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficina.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficina.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongTelefonoConyuge() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoConyuge.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoConyuge.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithLongFotografia() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongFotografia.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongFotografia.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongReferidoPor() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongReferidoPor.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongReferidoPor.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithShortPrimerNombre() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortSegundoNombre() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoNombre.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortPrimerApellido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortSegundoApellido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortApellidoCasada() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortApellidoCasada.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortHoraLocalizacion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortDireccion() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortDireccion.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortCelular() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortCelular.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortCelular.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortTelefonoCasa() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoCasa.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoCasa.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortTelefonoOficina() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficina.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficina.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithLongTelefonoOficinaExtension() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficinaExtension.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficinaExtension.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortTelefonoOficinaExtension() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficinaExtension.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficinaExtension.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithShortTelefonoConyuge() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoConyuge.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoConyuge.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortFotografia() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortFotografia.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortFotografia.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithShortReferidoPor() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortReferidoPor.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortReferidoPor.json");
	}
	
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidEmail() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidEmail.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidEmail.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidFechaNacimiento() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaNacimiento.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidFechaAniversario() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaAniversario.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithInvalidFechaClientePreferido() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaClientePreferido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithFutureFechaNacimiento() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaNacimiento.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaNacimiento.json");
	}
	
	@Test
	@RunAsClient
	public void addClienteWithFutureFechaAniversario() {
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaAniversario.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaAniversario.json");
	}
	
	
	@Test
	@RunAsClient
	public void addExistentCliente() {
		
		resourceClient.resourcePath(PATH_RESOURCE).postWithFile(getPathFileRequest(PATH_RESOURCE, "cliente.json"));
		
		final Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(
				getPathFileRequest(PATH_RESOURCE, "cliente.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}
	
	@Test
	@RunAsClient
	public void updateValidCliente() {
		final Long id = addClienteAndGetId("cliente.json");
		findClienteAndAssertResponseWithCliente(id, lucia());

		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteMaria.json"));
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));

		findClienteAndAssertResponseWithCliente(id, maria());
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithNullPrimerNombre() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithNullPrimerApellido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithNullDireccion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullDireccion.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithNullHoraLocalizacion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithNullHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorNullHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidColorOjos() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorOjos.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorOjos.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidFormaCara() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFormaCara.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFormaCara.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidRangoEdad() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidRangoEdad.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidRangoEdad.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidTipoLabios() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoLabios.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoLabios.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidTipoPiel() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTipoPiel.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTipoPiel.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidTonoBase() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoBase.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoBase.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidTonoPiel() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidTonoPiel.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidTonoPiel.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidColorCabello() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidColorCabello.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidColorCabello.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongPrimerNombre() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongSegundoNombre() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoNombre.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongPrimerApellido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongSegundoApellido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongSegundoApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongSegundoApellido.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongApellidoCasada() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongApellidoCasada.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongApellidoCasada.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongHoraLocalizacion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongDireccion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongDireccion.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongEmail() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongEmail.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongEmail.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongCelular() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongCelular.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongCelular.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongTelefonoCasa() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoCasa.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoCasa.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongTelefonoOficina() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficina.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficina.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongTelefonoOficinaExtension() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoOficinaExtension.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoOficinaExtension.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortTelefonoOficinaExtension() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficinaExtension.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficinaExtension.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongTelefonoConyuge() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongTelefonoConyuge.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongTelefonoConyuge.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithLongFotografia() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongFotografia.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongFotografia.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithLongReferidoPor() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithLongReferidoPor.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorLongReferidoPor.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithShortPrimerNombre() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerNombre.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortSegundoNombre() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoNombre.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoNombre.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortPrimerApellido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortPrimerApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortPrimerApellido.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortSegundoApellido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortSegundoApellido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortSegundoApellido.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortApellidoCasada() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortApellidoCasada.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortApellidoCasada.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortHoraLocalizacion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortHoraLocalizacion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortHoraLocalizacion.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortDireccion() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortDireccion.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortDireccion.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortCelular() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortCelular.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortCelular.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortTelefonoCasa() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoCasa.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoCasa.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortTelefonoOficina() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoOficina.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoOficina.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithShortTelefonoConyuge() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortTelefonoConyuge.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortTelefonoConyuge.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortFotografia() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortFotografia.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortFotografia.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithShortReferidoPor() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithShortReferidoPor.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorShortReferidoPor.json");
	}
	
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidEmail() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidEmail.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidEmail.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidFechaNacimiento() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaNacimiento.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidFechaAniversario() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaAniversario.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithInvalidFechaClientePreferido() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithInvalidFechaClientePreferido.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorInvalidFecha.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithFutureFechaNacimiento() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaNacimiento.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaNacimiento.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithFutureFechaAniversario() {
		final Long id = addClienteAndGetId("cliente.json");
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteWithFutureFechaAniversario.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteErrorFutureFechaAniversario.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteWithDataBelongingToOtherCliente() {
		final Long luciaId = addClienteAndGetId("cliente.json");
		addClienteAndGetId("clienteMaria.json");
		
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + luciaId).putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteMaria.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.VALIDATION_ERROR.getCode())));
		assertJsonResponseWithFile(response, "clienteAlreadyExists.json");
	}
	
	@Test
	@RunAsClient
	public void updateClienteNotFound() {
				
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/999").putWithFile(
				getPathFileRequest(PATH_RESOURCE, "clienteMaria.json"));

		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
		assertJsonResponseWithFile(response, "clienteNotFound.json");
	}
	
	@Test
	@RunAsClient
	public void findAllClientes() {
		resourceClient.resourcePath("DB/" + PATH_RESOURCE).postWithContent("");

		final Response response = resourceClient.resourcePath(PATH_RESOURCE).get();
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		assertResponseContainsTheClientes(response, 3, enieytilde(), lucia(), maria());
	}
	
	private void assertResponseContainsTheClientes(final Response response, final int expectedTotalRecords,
			final Cliente... expectedClientes) {
		final JsonObject result = JsonReader.readAsJsonObject(response.readEntity(String.class));

		final int totalRecords = result.getAsJsonObject("paging").get("totalRecords").getAsInt();
		assertThat(totalRecords, is(equalTo(expectedTotalRecords)));

		final JsonArray clientesList = result.getAsJsonArray("entries");
		assertThat(clientesList.size(), is(equalTo(expectedClientes.length)));

		for (int i = 0; i < expectedClientes.length; i++) {
			final Cliente expectedCliente = expectedClientes[i];
			assertThat(clientesList.get(i).getAsJsonObject().get("primerNombre").getAsString(),
					is(equalTo(expectedCliente.getPrimerNombre())));
		}

	}
	
	@Test
	@RunAsClient
	public void findClienteNotFound() {
				
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/999").get();
		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
		
	}
	
	
	@Test
	@RunAsClient
	public void deleteCliente() {
		
		final Long id = addClienteAndGetId("cliente.json");
		findClienteAndAssertResponseWithCliente(id, lucia());
		
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/" + id).delete();
		
		assertThat(response.getStatus(), is(equalTo(HttpCode.OK.getCode())));
		
	}
	
	
	@Test
	@RunAsClient
	public void deleteClienteNotFound() {
		
		final Response response = resourceClient.resourcePath(PATH_RESOURCE + "/999").delete();
		
		assertThat(response.getStatus(), is(equalTo(HttpCode.NOT_FOUND.getCode())));
		
	}
	
	private Long addClienteAndGetId(final String fileName) {
		return IntTestUtils.addElementWithFileAndGetId(resourceClient, PATH_RESOURCE, PATH_RESOURCE, fileName);
	}
	
	private void findClienteAndAssertResponseWithCliente(final Long clienteIdToBeFound,
			final Cliente expectedCliente) {
		final String json = IntTestUtils.findById(resourceClient, PATH_RESOURCE, clienteIdToBeFound);

		final JsonObject clienteAsJson = JsonReader.readAsJsonObject(json);
		assertThat(JsonReader.getStringOrNull(clienteAsJson, "primerApellido"), is(equalTo(expectedCliente.getPrimerApellido())));
	}
	
	private void assertJsonResponseWithFile(final Response response, final String fileName) {
		assertJsonMatchesFileContent(response.readEntity(String.class), getPathFileResponse(PATH_RESOURCE, fileName));
	}

}
