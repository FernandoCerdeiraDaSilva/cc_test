package cc_test.cc_rest_provider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.net.URI;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * PingRestServiceImpl Test
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = cc_test.config.SpringConfig.class)
public class PingRestServiceImplTest {

    @Autowired
    ApplicationContext context;
    
    public PingRestServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        HttpServer.start(context);
    }
    
    @After
    public void tearDown() {
        HttpServer.stop();
    }
    
    @Test
    public void testScenario01a_Add() {
        
        URI url = HttpServer.getUri().path("/ping").build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().get();
        System.out.println(response.toString());
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        System.out.println(response.readEntity(String.class));
    }
}
