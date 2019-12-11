package cc_test.cc_rest_provider;

import cc_test.cc_bo.BusinessException;
import cc_test.cc_bo.PanelBusinessService;
import cc_test.cc_dao.model.Panel;
import cc_test.cc_rest_api.PanelRestService;
import cc_test.cc_rest_api.dto.PanelDTO;

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

@Path("/panel/v1")
public class PanelRestServiceImpl implements PanelRestService {
    
    @Autowired
    private PanelBusinessService panelBusinessService;
    
    @Autowired
    private ModelHelper modelHelper;
    
    @Override
    @Path("/list")
    @Produces("application/json")
    @GET
    public Collection<PanelDTO> list() {
        return panelBusinessService.findAll().stream().map(post -> (PanelDTO)modelHelper.convert(post,Panel.class,PanelDTO.class)).collect(Collectors.toList());
    }
    
    @Override
    @Path("/get/{region_id}&{panel_id}")
    @Produces("application/json")
    @GET
    public PanelDTO get(@PathParam("region_id") Integer region_id, @PathParam("panel_id") Integer panel_id) {
        return (PanelDTO)modelHelper.convert(panelBusinessService.findById(region_id, panel_id),Panel.class,PanelDTO.class);
    }
    
    @Override
    @Path("/add")
    @Consumes("application/json")
    @POST
    public void add(PanelDTO panel) {
        try {
            panelBusinessService.add((Panel)modelHelper.convert(panel,PanelDTO.class,Panel.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
    @Override
    @Path("/update")
    @Consumes("application/json")
    @POST
    public void update(PanelDTO panel) {
        try {
            panelBusinessService.update((Panel)modelHelper.convert(panel,PanelDTO.class,Panel.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
    @Override
    @Path("/delete")
    @Consumes("application/json")
    @POST
    public void remove(PanelDTO panel) {
        try {
            panelBusinessService.remove((Panel)modelHelper.convert(panel,PanelDTO.class,Panel.class));
        } catch(BusinessException e) {
            // REMARKS: log the business exception message (principle throw earlier and catch later)
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR); // REMARKS: generic response due security issues.
        }
    }
    
}
