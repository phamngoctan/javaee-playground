package com.javaee.cdi.injectionpoint;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MyResourceTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(MyApplication.class, MyResource.class, HttpParam.class, HttpParamProducer.class);
    }

    private static WebTarget target;

    @ArquillianResource
    private URL base;

    @Before
    public void setUpClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "api/endpoint").toExternalForm()));
    }

    @Test
    public void testRequestWithQueryParam() {
        String r = target.queryParam("search", "Tan Pham").request().get(String.class);
        assertThat(r, equalTo("Tan Pham"));
    }

    @Test
    public void testRequestWithNoQueryParam() {
        String r = target.request().get(String.class);
        assertThat(r, equalTo(null));
    }

}