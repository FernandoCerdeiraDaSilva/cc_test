package cc_test.cc_rest_provider;

import cc_test.cc_rest_api.dto.RegionDTO;

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
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RegionRestServiceImpl Test
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = cc_test.config.SpringConfig.class)
public class RegionRestServiceImplTest {

    @Autowired
    ApplicationContext context;
    
    private String regionTestName = "RegionRestServiceImplTest-Region";
    
    public RegionRestServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        HttpServer.start(context);
    }
    
    @After
    public void tearDown() {
        HttpServer.stop();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Scenario 01 (Happy Path)
    ////////////////////////////////////////////////////////////////////////////

    private static RegionDTO regionPost = null;
    
    //
    // Scenario 01 - Add
    //
    @Test
    public void testScenario01a_Add() {
        
        assertNull(regionPost);
        regionPost = new RegionDTO();
        regionPost.setName(regionTestName);
        
        URI url = HttpServer.getUri().path("/region").path("/v1").path("/add").build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().post(Entity.entity(regionPost, MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.toString());
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        
        regionPost = response.readEntity(RegionDTO.class);
        assertNotNull(regionPost);
    }
    
    //
    // Scenario 01 - list
    //
    @Test
    public void testScenario01b_list() {
        
        assertNotNull(regionPost);
        
        URI url = HttpServer.getUri().path("/region").path("/v1").path("/list").build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().get();
        System.out.println(response.toString());
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        
        List<RegionDTO> list = response.readEntity(new GenericType<List<RegionDTO>>(){});
        
        boolean found = false;
        for(RegionDTO r : list) {
            if (r.equals(regionPost)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01c_Update() {
        
        assertNotNull(regionPost);
        regionPost.setName(regionPost.getName()+"X");
        
        URI url = HttpServer.getUri().path("/region").path("/v1").path("/update").build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().post(Entity.entity(regionPost, MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.toString());
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        
        regionPost = response.readEntity(RegionDTO.class);
        assertNotNull(regionPost);
    }
    
    //
    // Scenario 01 - get
    //
    @Test
    public void testScenario01d_get() {
        
        assertNotNull(regionPost);
        
        URI url = HttpServer.getUri().path("/region").path("/v1").path("/get").path("/".concat(regionPost.getId().toString())).build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().get();
        System.out.println(response.toString());
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        
        RegionDTO r = response.readEntity(RegionDTO.class);
        assertTrue(r.equals(regionPost));
        assertTrue(r.getName().endsWith("X"));
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01e_Delete() {
        
        assertNotNull(regionPost);
        
        URI url = HttpServer.getUri().path("/region").path("/v1").path("/delete").build();
        WebTarget target = ClientBuilder.newBuilder().build().target(url);
        Response response = target.request().post(Entity.entity(regionPost, MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response.toString());
        
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        regionPost = null;
    }
    
}
