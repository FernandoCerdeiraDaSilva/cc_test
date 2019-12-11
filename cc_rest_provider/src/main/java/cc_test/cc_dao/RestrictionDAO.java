package cc_test.cc_dao;

import cc_test.cc_dao.model.NextId;
import cc_test.cc_dao.model.Panel;
import cc_test.cc_dao.model.Restriction;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Restriction DAO
 * @author Fernando
 */

@Repository(value = "restrictionDAO")
public class RestrictionDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Restriction> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Restriction.findAll");
        return qry.list();
    }
    
    @Transactional(readOnly = true)
    public Restriction findById(Panel panel, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Restriction.findById");
        qry.setParameter("region_id", panel.getRegion().getId());
        qry.setParameter("panel_id", panel.getId());
        qry.setParameter("restriction_id", id);
        List l = qry.list();
        if (l.size() > 0) {
            return (Restriction)l.get(0);
        } else {
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    public List<Restriction> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Restriction.findByName");
        qry.setParameter("name", "%"+name+"%");
        return qry.list();
    }
    
    @Transactional(readOnly = true)
    public List<Restriction> findByPanel(Panel panel) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Restriction.findByPanel");
        qry.setParameter("region_id", panel.getRegion().getId());
        qry.setParameter("panel_id", panel.getId());
        return qry.list();
    }
    
    @Transactional
    public void save(Restriction restriction) {
        Session session = sessionFactory.getCurrentSession();
        if (restriction.getId()==0) {
            Query qry = session.getNamedQuery("Restriction.nextId"); // REMARKS: see model comments
            qry.setParameter("region_id", restriction.getPanel().getRegion().getId());
            qry.setParameter("panel_id", restriction.getPanel().getId());
            NextId nextId = (NextId)qry.list().get(0);
            restriction.setId(nextId.getId());
            session.persist(restriction);
        } else {
            session.update(restriction);
        }
    }
    
    @Transactional
    public void remove(Restriction restriction) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(restriction);
    }
}
