package cc_test.cc_dao;

import cc_test.cc_dao.model.NextId;
import cc_test.cc_dao.model.Region;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Region DAO
 * @author Fernando
 */

@Repository("regionDAO")
public class RegionDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Region> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Region.findAll");
        List<Region> l = qry.list();
        return l;
    }
    
    @Transactional(readOnly = true)
    public Region findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Region.findById");
        qry.setParameter("region_id", id);
        List l = qry.list();
        if (l.size() > 0) {
            return (Region)l.get(0);
        } else {
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    public List<Region> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Region.findByName");
        qry.setParameter("name", "%"+name+"%");
        return qry.list();
    }
    
    @Transactional
    public void save(Region region) {
        Session session = sessionFactory.getCurrentSession();
        if (region.getId()==0) {
            Query qry = session.getNamedQuery("Region.nextId"); // REMARKS: see model comments
            NextId nextId = (NextId)qry.list().get(0);
            region.setId(nextId.getId());
            session.persist(region);
        } else {
            session.update(region);
        }
    }
    
    @Transactional
    public void remove(Region region) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(region);
    }
}
