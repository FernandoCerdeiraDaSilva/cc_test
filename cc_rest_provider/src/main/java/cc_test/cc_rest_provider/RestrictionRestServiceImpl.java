package cc_test.cc_rest_provider;

import cc_test.cc_bo.BusinessException;
import cc_test.cc_bo.RestrictionBusinessService;
import cc_test.cc_dao.model.Panel;
import cc_test.cc_dao.model.Restriction;
import cc_test.cc_rest_api.RestrictionRestService;
import cc_test.cc_rest_api.dto.RestrictionDTO;

import java.util.Collection;
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

@Path("/restriction/v1")
public class RestrictionRestServiceImpl implements RestrictionRestService {
    
    @Autowired
    private RestrictionBusinessService restrictionBusinessService;
    
    @Autowired
    private ModelHelper modelHelper;
    
    @Override
    @Path("/list")
    @Produces("application/json")
    @GET
    public Collection<RestrictionDTO> list() {
        return restrictionBusinessService.findAll().stream().map(post -> (RestrictionDTO)modelHelper.convert(post,Restriction.class,RestrictionDTO.class)).collect(Collectors.toList());
    }
    
    @Override
    @Path("/get/{region_id}&{panel_id}&{restriction_id}")
    @Produces("application/json")
    @GET
    public RestrictionDTO get(@PathParam("region_id") Integer region_id, @PathParam("panel_id") Integer panel_id, @PathParam("restriction_id") Integer restriction_id) {
        return (RestrictionDTO)modelHelper.convert(restrictionBusinessService.findById(region_id, panel_id, restriction_id),Restriction.class,RestrictionDTO.class);
    }
    
    @Override
    @Path("/add")
    @Consumes("application/json")
    @POST
    public void add(RestrictionDTO restriction) {
        try {
            restrictionBusinessService.add((Restriction)modelHelper.convert(restriction,RestrictionDTO.class,Restriction.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
    @Override
    @Path("/update")
    @Consumes("application/json")
    @POST
    public void update(RestrictionDTO restriction) {
        try {
            restrictionBusinessService.update((Restriction)modelHelper.convert(restriction,RestrictionDTO.class,Restriction.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
    @Override
    @Path("/delete")
    @Consumes("application/json")
    @POST
    public void remove(RestrictionDTO restriction) {
        try {
            restrictionBusinessService.remove((Restriction)modelHelper.convert(restriction,RestrictionDTO.class,Restriction.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
}
