package cc_test.cc_dao.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Panel Primary Key
 * @author Fernando
 */
@Embeddable
public class PanelPK implements Serializable {
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Region.class, optional = false)
    @JoinColumns({
        @JoinColumn( name = "region_id", referencedColumnName = "region_id", insertable = false, updatable = false, nullable = false)
    })
    private Region region;
    
    @Column(name = "panel_id", insertable = true, updatable = false, nullable = false)
    private Integer id;
    
    public PanelPK() {
    }
    
    public PanelPK(Region region, Integer id) {
        this.region = region;
        this.id = id;
    }
    
    public PanelPK(Region region) {
        this.region = region;
        this.id = 0;
    }
    
    public Region getRegion() {
        return region;
    }
    
    public void setRegion(Region region) {
        this.region = region;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Panel)) return false;
        return (hashCode()==obj.hashCode());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getProperties());
    }
    
    private Object[] getProperties() {
        Object[] result = {
            region,
            id
        };
        return result;
    }
}
