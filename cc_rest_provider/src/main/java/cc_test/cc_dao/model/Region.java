package cc_test.cc_dao.model;

import java.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 * Region Entity
 * @author Fernando
 */
@Entity
@Table(name = "region", catalog = "", schema = "")
@NamedNativeQueries({
    @ NamedNativeQuery(name = "Region.findAll", query = "SELECT * FROM region", resultClass = Region.class),
    @ NamedNativeQuery(name = "Region.findById", query = "SELECT * FROM region WHERE region_id = :region_id", resultClass = Region.class),
    @ NamedNativeQuery(name = "Region.findByName", query = "SELECT * FROM region WHERE name LIKE :name", resultClass = Region.class),
    @ NamedNativeQuery(name = "Region.nextId", query = "SELECT (IFNULL(MAX(region_id),0)+1) AS nextId FROM region", resultClass = NextId.class) // REMARKS: the correct is to use @GeneratedValue and/or DB Sequence according to business/performance requirements.
})
public class Region implements Serializable {
    
    @Id
    @Column(name = "region_id", insertable = true, updatable = false, nullable = false)
    private Integer id;
    
    @Column(name = "name", insertable = true, updatable = true, nullable = false)
    private String name;

    @Column(name = "country", insertable = true, updatable = true, nullable = true)
    private String country;
    
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Panel.class, mappedBy = "pk.region")
    private List<Panel> panels = new ArrayList<Panel>();
    
    public Region() {
    }
    
    public Region(String name) {
        this.id = 0;
        this.name = name;
    }
    
    public Region(Integer id, String name) {
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

    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public List<Panel> getPanels() {
        return panels;
    }
    
    @Override
    public String toString() {
        return getProperties().toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Region)) return false;
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
