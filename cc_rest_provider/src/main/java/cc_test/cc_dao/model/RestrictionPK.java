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
 * Restriction Primary Key
 * @author Fernando
 */
@Embeddable
public class RestrictionPK implements Serializable {
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Panel.class)
    @JoinColumns({
        @JoinColumn( name = "region_id", referencedColumnName = "region_id", insertable = false, updatable = false, nullable = false),
        @JoinColumn( name = "panel_id" , referencedColumnName = "panel_id", insertable = false, updatable = false, nullable = false)
    })
    private Panel panel;
    
    @Column(name = "restriction_id", insertable = true, updatable = false, nullable = false)
    private Integer id;
    
    public RestrictionPK() {
    }
    
    public RestrictionPK(Panel panel, Integer id) {
        this.panel = panel;
        this.id = id;
    }
    
    public RestrictionPK(Panel panel) {
        this.panel = panel;
        this.id = 0;
    }
    
    public Panel getPanel() {
        return panel;
    }
    
    public void setPanel(Panel panel) {
        this.panel = panel;
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
            panel,
            id
        };
        return result;
    }
}
