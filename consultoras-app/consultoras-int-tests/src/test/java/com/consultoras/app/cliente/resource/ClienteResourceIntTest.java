package com.consultoras.app.cliente.resource;

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

import com.consultoras.app.common.model.HttpCode;
import com.consultoras.app.commontests.utils.ResourceClient;
import com.consultoras.app.commontests.utils.ResourceDefinitions;

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

	}
	
	@Test
	@RunAsClient
	public void addValidClientAndFindIt(){
		
		Response response = resourceClient.resourcePath(PATH_RESOURCE).postWithFile(getPathFileRequest(PATH_RESOURCE, "cliente.json"));
		assertThat(response.getStatus(), is(equalTo(HttpCode.CREATED.getCode())));
		
		
		
	}

}
