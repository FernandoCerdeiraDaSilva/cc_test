package cc_test.cc_dao;

import cc_test.cc_dao.model.Panel;
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
 * UNit Test - PanelDAO
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = SpringConfig.class)
public class PanelDAOTest {

    @Autowired
    private PanelDAO panelDAO;
    
    @Autowired
    private RegionDAO regionDAO;
    
    private String panelTestName = "PanelDAOTest-Panel";
    private String regionTestName = "PanelDAOTest-Region";
    
    public PanelDAOTest() {
    }
    
    //
    // Scenario 01 - Start
    //
    @Test
    public void testScenario01a_Start() {
        
        List<Panel> panelList1 = panelDAO.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelDAO.remove(panel);
        }
        List<Panel> panelList2 = panelDAO.findByName(regionTestName);
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
        
        assertNotEquals(new Integer(0), new Integer(panel.getId()));
    }
    
    //
    // Scenario 01 - FindByName
    //
    @Test
    public void testScenario01c_FindByName() {
        List<Panel> panelList = panelDAO.findByName(panelTestName);
        assertTrue(panelList.size()>0);
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
                Panel check = panelDAO.findById(region, panel.getId());
                assertNotNull(check);
                assertEquals(check,panel);
                assertTrue(check.getRestrictions().size()>=0);
            }
        }
    }
    
    //
    // Scenario 01 - FindByRegion
    //
    @Test
    public void testScenario01e_FindByRegion() {
        List<Region> regionList = regionDAO.findByName(regionTestName);
        for(Region region : regionList) {
            List<Panel> panelList = panelDAO.findByRegion(region);
            assertTrue(panelList.size()>0);
            assertEquals(new Integer(region.getPanels().size()), new Integer(panelList.size()));
        }
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01f_Update() {
        List<Panel> panelList = panelDAO.findByName(panelTestName);
        assertTrue(panelList.size()>0);
        for(Panel panel : panelList) {
            panel.setName(panel.getName()+"X");
            panelDAO.save(panel);
            Panel check = panelDAO.findById(panel.getRegion(), panel.getId());
            assertNotNull(check);
            assertEquals(check,panel);
            assertTrue(check.getName().endsWith("X"));
        }
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01g_Delete() {
        List<Panel> panelList1 = panelDAO.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelDAO.remove(panel);
        }
        List<Panel> panelList2 = panelDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionDAO.findByName(regionTestName);
        for(Region region : regionList1) {
            regionDAO.remove(region);
        }
        List<Region> regionList2 = regionDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
}
