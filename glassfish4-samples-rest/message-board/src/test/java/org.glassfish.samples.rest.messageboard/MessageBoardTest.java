package org.glassfish.samples.rest.messageboard;

import org.glassfish.samples.rest.messageboard.entities.Message;
import org.glassfish.samples.rest.messageboard.entities.MessageListWriter;
import org.glassfish.samples.rest.messageboard.entities.MessageWriter;
import org.glassfish.samples.rest.messageboard.exceptions.NotFoundException;
import org.glassfish.samples.rest.messageboard.exceptions.NotFoundExceptionMapper;
import org.glassfish.samples.rest.messageboard.filters.ResponseFilter;
import org.glassfish.samples.rest.messageboard.interceptors.ValidCharacterInterceptor;
import org.glassfish.samples.rest.messageboard.resources.JaxRsApplication;
import org.glassfish.samples.rest.messageboard.resources.MessageBoardResourceBean;
import org.glassfish.samples.rest.messageboard.resources.MessageBoardRootResource;
import org.glassfish.samples.rest.messageboard.resources.MessageHolderSingletonBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageBoardTest {

    private static WebTarget target;


    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(Message.class)
                .addClass(MessageListWriter.class)
                .addClass(MessageWriter.class)
                .addClass(NotFoundException.class)
                .addClass(NotFoundExceptionMapper.class)
                .addClass(ResponseFilter.class)
                .addClass(ValidCharacterInterceptor.class)
                .addClass(JaxRsApplication.class)
                .addClass(MessageBoardResourceBean.class)
                .addClass(MessageBoardRootResource.class)
                .addClass(MessageHolderSingletonBean.class);

        System.out.println(war.toString(true));

        return war;
    }


    @ArquillianResource
    private URL base;


    @Before
    public void setupClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "app/messages").toExternalForm()));
    }

    @Test
    public void test1Post() {
        target.request().post(Entity.text("Hi"));
        String r = target.path("3").request().get(String.class);
        assertEquals("{\"created\":???,\"uniqueId\":3,\"message\":\"Hi\"}", r);
    }

    @Test
    public void test2GetList() {
        String r = target.request().get(String.class);
        assertEquals("[{\"uniqueId\":3},{\"uniqueId\":2},{\"uniqueId\":1},{\"uniqueId\":0}]", r);
    }
}
