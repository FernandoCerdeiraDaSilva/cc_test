package cc_test.cc_dao.model;

import java.util.Objects;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 * Entity Restriction
 * @author Fernando
 */
@Entity
@Table(name = "restriction", catalog = "", schema = "")
@NamedNativeQueries({
    @ NamedNativeQuery(name = "Restriction.findAll", query = "SELECT * FROM restriction", resultClass = Restriction.class),
    @ NamedNativeQuery(name = "Restriction.findById", query = "SELECT * FROM restriction WHERE region_id = :region_id AND panel_id = :panel_id AND restriction_id = :restriction_id", resultClass = Restriction.class),
    @ NamedNativeQuery(name = "Restriction.findByName", query = "SELECT * FROM restriction WHERE name LIKE :name", resultClass = Restriction.class),
    @ NamedNativeQuery(name = "Restriction.findByPanel", query = "SELECT * FROM restriction WHERE region_id = :region_id AND panel_id = :panel_id", resultClass = Restriction.class),
    @ NamedNativeQuery(name = "Restriction.nextId", query = "SELECT (IFNULL(MAX(restriction_id),0)+1) AS nextId FROM restriction WHERE region_id = :region_id AND panel_id = :panel_id", resultClass = NextId.class) // REMARKS: the correct is to use @GeneratedValue and/or DB Sequence according to business/performance requirements.
})
public class Restriction implements Serializable {

    @EmbeddedId
    private RestrictionPK pk = new RestrictionPK();
    
    @Column(name = "name", insertable = true, updatable = true, nullable = false)
    private String name;

    @Column(name = "description", insertable = true, updatable = true, nullable = true)
    private String description;

    @Column(name = "type", insertable = true, updatable = true, nullable = true)
    private String type;
    
    public Restriction() {
    }
    
    public Restriction(Panel panel, String name) {
        this.pk = new RestrictionPK(panel);
        this.name = name;
    }
    
    public Restriction(Panel panel, Integer id, String name) {
        this.pk = new RestrictionPK(panel, id);
        this.name = name;
    }

    public Panel getPanel() {
        return pk.getPanel();
    }
    
    public void setPanel(Panel panel) {
        this.pk.setPanel(panel);
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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Restriction)) return false;
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
