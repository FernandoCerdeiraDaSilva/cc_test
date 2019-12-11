package cc_test.cc_dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Subselect;

/**
 * "Temp" Entity
 * @author Fernando
 */
@Entity
@Subselect(value = "")
public class NextId {
    
    @Id
    @Column(name = "nextId", insertable = false, updatable = false, nullable = false)
    private Integer nextId;
    
    public NextId() {
    }
    
    public Integer getId() {
        return nextId;
    }
    
    public void setId(Integer id) {
        this.nextId = id;
    }
    
}
