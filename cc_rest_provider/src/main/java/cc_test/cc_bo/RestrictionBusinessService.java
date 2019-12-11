package cc_test.cc_bo;

import cc_test.cc_dao.PanelDAO;
import cc_test.cc_dao.RegionDAO;
import cc_test.cc_dao.RestrictionDAO;
import cc_test.cc_dao.model.*;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Restriction Business Service
 * @author Fernando
 */

@Service("restrictionBusinessService")
public class RestrictionBusinessService {
    
    @Autowired
    private RestrictionDAO restrictionDAO;
    
    @Autowired
    private PanelDAO panelDAO;
    
    @Autowired
    private RegionDAO regionDAO;
    
    public List<Restriction> findAll() {
        return restrictionDAO.findAll();
    }
    
    public Restriction findById(Integer region_id, Integer panel_id, Integer restriction_id) {
        Region region = regionDAO.findById(region_id);
        Panel panel = panelDAO.findById(region, panel_id);
        return restrictionDAO.findById(panel, restriction_id);
    }

    public List<Restriction> findByName(String name) {
        return restrictionDAO.findByName(name);
    }
    
    public void add(Restriction restriction) throws BusinessException {
        if ((restriction==null)||(restriction.getId()!=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        restrictionDAO.save(restriction);
    }

    public void update(Restriction restriction) throws BusinessException {
        if ((restriction==null)||(restriction.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        restrictionDAO.save(restriction);
    }
    
    public void remove(Restriction restriction) throws BusinessException {
        if ((restriction==null)||(restriction.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        restrictionDAO.remove(restriction);
    }
    
}
