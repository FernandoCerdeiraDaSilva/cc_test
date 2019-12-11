package cc_test.cc_rest_api;

import cc_test.cc_rest_api.dto.RestrictionDTO;
import java.util.Collection;

/**
 * Interface describing RestrictionRestService
 * @author Fernando
 */
public interface RestrictionRestService {
    
    Collection<RestrictionDTO> list();
    
    RestrictionDTO get(Integer region_id, Integer panel_id, Integer restriction_id);
    
    void add(RestrictionDTO restriction);
    
    void update(RestrictionDTO restriction);
    
    void remove(RestrictionDTO restriction);
    
}
