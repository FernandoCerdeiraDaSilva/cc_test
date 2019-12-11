package cc_test.cc_rest_api.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Panel DTO
 * @author Fernando
 */

@XmlRootElement(name = "Panel")
public class PanelDTO {

    private Integer id;
    private String name;
    private Collection<RestrictionDTO> restrictions;
    
    public PanelDTO() {
    }
    
    public PanelDTO(String name) {
        this.id = 0;
        this.name = name;
        this.restrictions = new ArrayList<>();
    }
    
    public PanelDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.restrictions = new ArrayList<>();
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
    
    public Collection<RestrictionDTO> getRestrictions() {
        return restrictions;
    }
    
    public void setRestrictions(Collection<RestrictionDTO> restrictions) {
        this.restrictions = restrictions;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PanelDTO)) return false;
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
