package cc_test.cc_rest_api;

import cc_test.cc_rest_api.dto.RegionDTO;
import java.util.Collection;

/**
 * Interface describing the RegionRestService
 * @author Fernando
 */
public interface RegionRestService {
    
    Collection<RegionDTO> list();
    
    RegionDTO get(Integer id);
    
    RegionDTO add(RegionDTO region);
    
    RegionDTO update(RegionDTO region);
    
    void remove(RegionDTO region);
    
}
