package cc_test.cc_rest_provider;

import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.TestProperties;

import org.springframework.context.ApplicationContext;

/**
 * HttpServer Mock Tests
 * @author Fernando
 */
public class HttpServer {

    private static org.glassfish.grizzly.http.server.HttpServer server = null;
    private static UriBuilder uri = UriBuilder.fromUri("http://localhost/").port(8081).path("/cc_test").path("/webresources");
    
    public static UriBuilder getUri() {
        return uri;
    }
    
    public static void start(ApplicationContext context) {
        ResourceConfig rc = new ResourceConfig();
        rc.packages("");
        rc.property("contextConfig", context);
        rc.property("contextConfigLocation", "WEB-INF/application-context.xml" );
        rc.property("jersey.config.server.tracing.type", "ALL");
	rc.property("jersey.config.server.tracing.threshold", "VERBOSE");
        rc.property(TestProperties.LOG_TRAFFIC, "true");
        rc.property(TestProperties.DUMP_ENTITY, "true");
        rc.property(TestProperties.RECORD_LOG_LEVEL, "ALL");
        //enable(TestProperties.LOG_TRAFFIC);
        //enable(TestProperties.DUMP_ENTITY);
        rc.register(PingRestServiceImpl.class);
        rc.register(RegionRestServiceImpl.class);
        rc.register(PanelRestServiceImpl.class);
        rc.register(RestrictionRestServiceImpl.class);
        server = GrizzlyHttpServerFactory.createHttpServer(uri.build(), rc);
    }
    
    public static void stop() {
        server.shutdownNow();
        server = null;
    }
}
