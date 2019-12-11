package cc_test.cc_rest_api;

import cc_test.cc_rest_api.dto.PanelDTO;
import java.util.Collection;

/**
 * Interface describing the PanelRestService
 * @author Fernando
 */
public interface PanelRestService {
    
    Collection<PanelDTO> list();
    
    PanelDTO get(Integer region_id, Integer panel_id);
    
    void add(PanelDTO panel);
    
    void update(PanelDTO panel);
    
    void remove(PanelDTO panel);
    
}
