package cc_test.cc_dao;

import cc_test.cc_dao.model.Panel;
import cc_test.cc_dao.model.Restriction;
import cc_test.cc_dao.model.Region;
import cc_test.config.SpringConfig;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * RestrictionDAO Test
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = SpringConfig.class)
public class RestrictionDAOTest {

    @Autowired
    private RestrictionDAO restrictionDAO;
    
    @Autowired
    private PanelDAO panelDAO;
    
    @Autowired
    private RegionDAO regionDAO;

    private String restrictionTestName = "RestrictionDAOTest-Restriction";
    private String panelTestName = "RestrictionDAOTest-Panel";
    private String regionTestName = "RestrictionDAOTest-Region";
    
    public RestrictionDAOTest() {
    }
    
    //
    // Scenario 01 - Start
    //
    @Test
    public void testScenario01a_Start() {
        List<Restriction> restrictionList1 = restrictionDAO.findByName(restrictionTestName);
        for(Restriction restriction : restrictionList1) {
            restrictionDAO.remove(restriction);
        }
        List<Restriction> restrictionList2 = restrictionDAO.findByName(restrictionTestName);
        assertEquals(new Integer(0), new Integer(restrictionList2.size()));
        
        List<Panel> panelList1 = panelDAO.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelDAO.remove(panel);
        }
        List<Panel> panelList2 = panelDAO.findByName(panelTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionDAO.findByName(regionTestName);
        for(Region region : regionList1) {
            regionDAO.remove(region);
        }
        List<Region> regionList2 = regionDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
        
        List<Panel> result = panelDAO.findAll();
        assertNotNull(result);
    }
    
    //
    // Scenario 01 - Insert
    //
    @Test
    public void testScenario01b_Insert() {
        
        Region region = new Region(regionTestName);
        regionDAO.save(region);
        
        Panel panel = new Panel(region, panelTestName);
        panelDAO.save(panel);
        
        Restriction restriction = new Restriction(panel, restrictionTestName);
        restrictionDAO.save(restriction);
        
        assertNotEquals(new Integer(0), new Integer(restriction.getId()));
    }
    
    //
    // Scenario 01 - FindByName
    //
    @Test
    public void testScenario01c_FindByName() {
        List<Restriction> restrictionList = restrictionDAO.findByName(restrictionTestName);
        assertTrue(restrictionList.size()>0);
    }
    
    //
    // Scenario 01 - FindById
    //
    @Test
    public void testScenario01d_FindById() {
        List<Region> regionList = regionDAO.findByName(regionTestName);
        assertTrue(regionList.size()>0);
        for(Region region : regionList) {
            for(Panel panel : region.getPanels()) {
                for(Restriction restriction : panel.getRestrictions()) {
                    Restriction check = restrictionDAO.findById(panel, restriction.getId());
                    assertNotNull(check);
                    assertEquals(check,restriction);
                }
            }
        }
    }
    
    //
    // Scenario 01 - FindByPanel
    //
    @Test
    public void testScenario01e_FindByPanel() {
        List<Panel> panelList = panelDAO.findByName(panelTestName);
        for(Panel panel : panelList) {
            List<Restriction> restrictionList = restrictionDAO.findByPanel(panel);
            assertTrue(restrictionList.size()>0);
            assertEquals(new Integer(panel.getRestrictions().size()), new Integer(restrictionList.size()));
        }
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01f_Update() {
        List<Restriction> restrictionList = restrictionDAO.findByName(restrictionTestName);
        assertTrue(restrictionList.size()>0);
        for(Restriction restriction : restrictionList) {
            restriction.setName(restriction.getName()+"X");
            restrictionDAO.save(restriction);
            Restriction check = restrictionDAO.findById(restriction.getPanel(), restriction.getId());
            assertNotNull(check);
            assertEquals(check,restriction);
            assertTrue(check.getName().endsWith("X"));
        }
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01g_Delete() {
        List<Restriction> restrictionList1 = restrictionDAO.findByName(restrictionTestName);
        for(Restriction restriction : restrictionList1) {
            restrictionDAO.remove(restriction);
        }
        List<Restriction> restrictionList2 = restrictionDAO.findByName(restrictionTestName);
        assertEquals(new Integer(0), new Integer(restrictionList2.size()));
        
        List<Panel> panelList1 = panelDAO.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelDAO.remove(panel);
        }
        List<Panel> panelList2 = panelDAO.findByName(panelTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionDAO.findByName(regionTestName);
        for(Region region : regionList1) {
            regionDAO.remove(region);
        }
        List<Region> regionList2 = regionDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
}
