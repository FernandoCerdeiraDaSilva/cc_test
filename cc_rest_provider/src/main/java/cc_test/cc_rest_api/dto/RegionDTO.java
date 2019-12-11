package cc_test.cc_rest_api.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Region DTO
 * @author Fernando
 */

@XmlRootElement(name = "Region")
public class RegionDTO {

    private Integer id;
    private String name;
    private Collection<PanelDTO> panels;

    public RegionDTO() {
        this.id = 0;
        this.name = "";
        this.panels = new ArrayList<>();
    }
    
    public RegionDTO(String name) {
        this.id = 0;
        this.name = name;
        this.panels = new ArrayList<>();
    }
    
    public RegionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.panels = new ArrayList<>();
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
    
    public Collection<PanelDTO> getPanels() {
        return panels;
    }
    
    public void setPanels(Collection<PanelDTO> panels) {
        this.panels = panels;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RegionDTO)) return false;
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
