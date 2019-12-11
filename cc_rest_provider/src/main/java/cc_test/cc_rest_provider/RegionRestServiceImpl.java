package cc_test.cc_rest_provider;

import cc_test.cc_bo.BusinessException;
import cc_test.cc_bo.RegionBusinessService;
import cc_test.cc_dao.model.Region;
import cc_test.cc_rest_api.dto.RegionDTO;
import cc_test.cc_rest_api.RegionRestService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/region/v1")
public class RegionRestServiceImpl implements RegionRestService {
    
    @Autowired
    private RegionBusinessService regionBusinessService;
    
    @Autowired
    private ModelHelper modelHelper;
    
    @Override
    @Path("/list")
    @Produces("application/json")
    @GET
    public Collection<RegionDTO> list() {
        List<Region> l = regionBusinessService.findAll();
        Collection<RegionDTO> c = l.stream().map(post -> (RegionDTO)modelHelper.convert(post,Region.class,RegionDTO.class)).collect(Collectors.toList());
        return c;
    }
    
    @Override
    @Path("/get/{id}")
    @Produces("application/json")
    @GET
    public RegionDTO get(@PathParam("id") Integer id) {
        Region o = regionBusinessService.findById(id);
        RegionDTO r = (RegionDTO)modelHelper.convert(o,Region.class,RegionDTO.class);
        return r;
    }
    
    @Override
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    public RegionDTO add(RegionDTO regionPost) {
        Region region = (Region)modelHelper.convert(regionPost,RegionDTO.class,Region.class);
        try {
            regionBusinessService.add(region);
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
        RegionDTO r = (RegionDTO)modelHelper.convert(region,Region.class,RegionDTO.class);
        return r;
    }
    
    @Override
    @Path("/update")
    @Consumes("application/json")
    @Produces("application/json")
    @POST
    public RegionDTO update(RegionDTO regionPost) {
        Region region = (Region)modelHelper.convert(regionPost,RegionDTO.class,Region.class);
        try {
            regionBusinessService.update(region);
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
        return (RegionDTO)modelHelper.convert(region,Region.class,RegionDTO.class);
    }
    
    @Override
    @Path("/delete")
    @Consumes("application/json")
    @POST
    public void remove(RegionDTO region) {
        try {
            regionBusinessService.remove((Region)modelHelper.convert(region,RegionDTO.class,Region.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
}
