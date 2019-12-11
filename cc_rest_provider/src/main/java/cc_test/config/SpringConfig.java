package cc_test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringDaoConfig file
 * @author Fernando
 */

@Configuration()
@ImportResource(value = "WEB-INF/application-context.xml")
public class SpringConfig {
    
}
