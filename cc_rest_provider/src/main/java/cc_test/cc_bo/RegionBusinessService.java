package cc_test.cc_bo;

import cc_test.cc_dao.PanelDAO;
import cc_test.cc_dao.RegionDAO;
import cc_test.cc_dao.model.*;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Region Business Service
 * @author Fernando
 */

@Service("regionBusinessService")
public class RegionBusinessService {
    
    @Autowired
    private RegionDAO regionDAO;
    
    @Autowired
    private PanelDAO panelDAO;
    
    public List<Region> findAll() {
        return regionDAO.findAll();
    }
    
    public Region findById(Integer id) {
        return regionDAO.findById(id);
    }

    public List<Region> findByName(String name) {
        return regionDAO.findByName(name);
    }
    
    public void add(Region region) throws BusinessException {
        if ((region==null)||(region.getId()!=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        regionDAO.save(region);
    }

    public void update(Region region) throws BusinessException {
        if ((region==null)||(region.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        regionDAO.save(region);
    }
    
    public void remove(Region region) throws BusinessException {
        if ((region==null)||(region.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        if ((region.getPanels().size()>0)||(panelDAO.findByRegion(region).size()>0)) {
            throw new BusinessException("Foreign Key constraint violated.");
        }
        regionDAO.remove(region);
    }

}
