package cc_test.cc_dao.model;

import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 * Entity Panel
 * @author Fernando
 */
@Entity
@Table(name = "panel", catalog = "", schema = "")
@NamedNativeQueries({
    @NamedNativeQuery(name = "Panel.findAll", query = "SELECT * FROM panel", resultClass = Panel.class),
    @NamedNativeQuery(name = "Panel.findById", query = "SELECT * FROM panel WHERE region_id = :region_id AND panel_id = :panel_id", resultClass = Panel.class),
    @NamedNativeQuery(name = "Panel.findByName", query = "SELECT * FROM panel WHERE name LIKE :name", resultClass = Panel.class),
    @NamedNativeQuery(name = "Panel.findByRegion", query = "SELECT * FROM panel WHERE region_id = :region_id", resultClass = Panel.class),
    @NamedNativeQuery(name = "Panel.nextId", query = "SELECT (IFNULL(MAX(panel_id),0)+1) AS nextId FROM panel WHERE region_id = :region_id", resultClass = NextId.class) // REMARKS: the correct is to use @GeneratedValue and/or DB Sequence according to business/performance requirements.
})
public class Panel implements Serializable {
    
    @EmbeddedId
    private PanelPK pk = new PanelPK();
    
    @Column(name = "name", insertable = true, updatable = true, nullable = false)
    private String name;

    @Column(name = "status", insertable = true, updatable = true, nullable = true)
    private String status;
    
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Restriction.class, mappedBy = "pk.panel")
    private List<Restriction> restrictions = new ArrayList<Restriction>();
    
    public Panel() {
    }
    
    public Panel(Region region, String name) {
        this.pk = new PanelPK(region);
        this.name = name;
    }
    
    public Panel(Region region, Integer id, String name) {
        this.pk = new PanelPK(region, id);
        this.name = name;
    }
    
    public Region getRegion() {
        return pk.getRegion();
    }
    
    public void setRegion(Region region) {
        this.pk.setRegion(region);
    }
    
    public Integer getId() {
        return pk.getId();
    }
    
    public void setId(Integer id) {
        this.pk.setId(id);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<Restriction> getRestrictions() {
        return restrictions;
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
            pk,
            name
        };
        return result;
    }
}
