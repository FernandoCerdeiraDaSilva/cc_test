package cc_test.cc_rest_provider;

import cc_test.cc_rest_api.PingRestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/ping")
public class PingRestServiceImpl implements PingRestService {
    @Override
    @Produces("text/plain")
    @GET
    public String ping() {
        return "Clear Channel Test - Fernando, the best fit :-) ";
    }
}
