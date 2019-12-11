package cc_test.cc_bo;

import cc_test.cc_dao.PanelDAO;
import cc_test.cc_dao.RegionDAO;
import cc_test.cc_dao.RestrictionDAO;
import cc_test.cc_dao.model.*;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Panel Business Service
 * @author Fernando
 */

@Service("panelBusinessService")
public class PanelBusinessService {
    
    @Autowired
    private PanelDAO panelDAO;
    
    @Autowired
    private RegionDAO regionDAO;
    
    @Autowired
    private RestrictionDAO restrictionDAO;
    
    public List<Panel> findAll() {
        return panelDAO.findAll();
    }
    
    public Panel findById(Integer region_id, Integer panel_id) {
        Region region = regionDAO.findById(region_id);
        return panelDAO.findById(region, panel_id);
    }

    public List<Panel> findByName(String name) {
        return panelDAO.findByName(name);
    }
    
    public void add(Panel panel) throws BusinessException {
        if ((panel==null)||(panel.getId()!=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        panelDAO.save(panel);
    }

    public void update(Panel panel) throws BusinessException {
        if ((panel==null)||(panel.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        panelDAO.save(panel);
    }
    
    public void remove(Panel panel) throws BusinessException {
        if ((panel==null)||(panel.getId()<=0)) {
            throw new BusinessException("Model not formatted correctly.");
        }
        if ((panel.getRestrictions().size()>0)||(restrictionDAO.findByPanel(panel).size()>0)) {
            throw new BusinessException("Foreign Key constraint violated.");
        }
        panelDAO.remove(panel);
    }
    
}
