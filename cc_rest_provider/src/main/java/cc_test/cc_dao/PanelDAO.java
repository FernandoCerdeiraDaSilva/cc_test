package cc_test.cc_dao;

import cc_test.cc_dao.model.NextId;
import cc_test.cc_dao.model.Panel;
import cc_test.cc_dao.model.Region;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Panel DAO
 * @author Fernando
 */

@Repository(value = "panelDAO")
public class PanelDAO {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public List<Panel> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Panel.findAll");
        return qry.list();
    }
    
    @Transactional(readOnly = true)
    public Panel findById(Region region, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Panel.findById");
        qry.setParameter("region_id", region.getId());
        qry.setParameter("panel_id", id);
        List l = qry.list();
        if (l.size() > 0) {
            return (Panel)l.get(0);
        } else {
            return null;
        }
    }
    
    @Transactional(readOnly = true)
    public List<Panel> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Panel.findByName");
        qry.setParameter("name", "%"+name+"%");
        return qry.list();
    }
    
    @Transactional(readOnly = true)
    public List<Panel> findByRegion(Region region) {
        Session session = sessionFactory.getCurrentSession();
        Query qry = session.getNamedQuery("Panel.findByRegion");
        qry.setParameter("region_id", region.getId());
        return qry.list();
    }
    
    @Transactional
    public void save(Panel panel) {
        Session session = sessionFactory.getCurrentSession();
        if (panel.getId()==0) {
            Query qry = session.getNamedQuery("Panel.nextId"); // REMARKS: see model comments
            qry.setParameter("region_id", panel.getRegion().getId());
            NextId nextId = (NextId)qry.list().get(0);
            panel.setId(nextId.getId());
            session.persist(panel);
        } else {
            session.update(panel);
        }
    }
    
    @Transactional
    public void remove(Panel panel) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(panel);
    }
}
