package cc_test.cc_rest_provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DTO x Entity conversion Helper
 * @author Fernando
 */

@Component("modelHelper")
public class ModelHelper {

    @Autowired
    private ModelMapper modelMapper;
    
    public ModelMapper getInstance() {
        return modelMapper;
    }
    
    public Object convert(Object modelObj, Class modelClass, Class dtoClass) {
        Object dtoObj = modelMapper.map(modelClass.cast(modelObj), dtoClass);
        // REMARKS: here we can extend/aggregate, additional properties or even lists.
        return dtoObj;
    }
    
}
