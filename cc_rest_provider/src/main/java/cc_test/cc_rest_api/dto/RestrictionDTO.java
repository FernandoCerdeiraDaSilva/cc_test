package cc_test.cc_rest_api.dto;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restriction DTO
 * @author Fernando
 */

@XmlRootElement(name = "Restriction")
public class RestrictionDTO {

    private Integer id;
    private String name;

    public RestrictionDTO() {
    }
    
    public RestrictionDTO(String name) {
        this.id = 0;
        this.name = name;
    }
    
    public RestrictionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RestrictionDTO)) return false;
        return (hashCode()==obj.hashCode());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getProperties());
    }
    
    private Object[] getProperties() {
        Object[] result = {
            id,
            name
        };
        return result;
    }
}
