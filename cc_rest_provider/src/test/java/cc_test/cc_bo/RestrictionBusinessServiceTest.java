package cc_test.cc_bo;

import cc_test.cc_dao.model.*;
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

/**
 * Unit Test - RestrictionBusinessService
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = SpringConfig.class)
public class RestrictionBusinessServiceTest {
    
    @Autowired
    private RegionBusinessService regionBusinessService;

    @Autowired
    private PanelBusinessService panelBusinessService;
    
    @Autowired
    private RestrictionBusinessService restrictionBusinessService;
    
    private String regionTestName = "RestrictionBusinessServiceTest-Region";
    private String panelTestName = "RestrictionBusinessServiceTest-Panel";
    private String restrictionTestName = "RestrictionBusinessServiceTest-Restriction";
    
    public RestrictionBusinessServiceTest() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Scenario 01 (Happy Path)
    ////////////////////////////////////////////////////////////////////////////

    //
    // Scenario 01 - Start
    //
    @Test
    public void testScenario01a_Start() throws BusinessException {
        List<Restriction> restrictionList1 = restrictionBusinessService.findByName(restrictionTestName);
        for(Restriction restriction : restrictionList1) {
            restrictionBusinessService.remove(restriction);
        }
        List<Restriction> restrictionList2 = restrictionBusinessService.findByName(restrictionTestName);
        assertEquals(new Integer(0), new Integer(restrictionList2.size()));
        
        List<Restriction> result = restrictionBusinessService.findAll();
        assertNotNull(result);
    }
    
    //
    // Scenario 01 - Insert
    //
    @Test
    public void testScenario01b_Insert() throws BusinessException {
        Region region = new Region(regionTestName);
        regionBusinessService.add(region);
        Panel panel = new Panel(region, panelTestName);
        panelBusinessService.add(panel);
        Restriction restriction = new Restriction(panel, restrictionTestName);
        restrictionBusinessService.add(restriction);
        assertNotEquals(new Integer(0), new Integer(restriction.getId()));
    }
    
    //
    // Scenario 01 - FindByName
    //
    @Test
    public void testScenario01c_FindByName() {
        List<Restriction> restrictionList = restrictionBusinessService.findByName(restrictionTestName);
        assertTrue(restrictionList.size()>0);
    }
    
    //
    // Scenario 01 - FindById
    //
    @Test
    public void testScenario01d_FindById() {
        List<Restriction> restrictionList = restrictionBusinessService.findByName(restrictionTestName);
        assertTrue(restrictionList.size()>0);
        for(Restriction restriction : restrictionList) {
            Restriction check = restrictionBusinessService.findById(restriction.getPanel().getRegion().getId(), restriction.getPanel().getId(), restriction.getId());
            assertNotNull(check);
            assertEquals(check,restriction);
        }
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01e_Update() throws BusinessException {
        List<Restriction> restrictionList = restrictionBusinessService.findByName(restrictionTestName);
        assertTrue(restrictionList.size()>0);
        for(Restriction restriction : restrictionList) {
            restriction.setName(restriction.getName()+"X");
            restrictionBusinessService.update(restriction);
            Restriction check = restrictionBusinessService.findById(restriction.getPanel().getRegion().getId(), restriction.getPanel().getId(), restriction.getId());
            assertNotNull(check);
            assertEquals(check,restriction);
            assertTrue(check.getName().endsWith("X"));
        }
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01f_Delete() throws BusinessException {
        List<Restriction> restrictionList1 = restrictionBusinessService.findByName(restrictionTestName);
        for(Restriction restriction : restrictionList1) {
            restrictionBusinessService.remove(restriction);
        }
        List<Restriction> restrictionList2 = restrictionBusinessService.findByName(restrictionTestName);
        assertEquals(new Integer(0), new Integer(restrictionList2.size()));
        
        List<Panel> panelList1 = panelBusinessService.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelBusinessService.remove(panel);
        }
        List<Panel> panelList2 = panelBusinessService.findByName(panelTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionBusinessService.findByName(regionTestName);
        for(Region region : regionList1) {
            regionBusinessService.remove(region);
        }
        List<Region> regionList2 = regionBusinessService.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Scenario 02 (Exceptions)
    ////////////////////////////////////////////////////////////////////////////
    
    //
    // Scenario 02 - Exception on Add method
    //
    @Test(expected = BusinessException.class)
    public void testScenario02a_Add() throws BusinessException {
        Region region = new Region(regionTestName);
        Panel panel = new Panel(region, panelTestName);
        Restriction restriction = new Restriction(panel, restrictionTestName);
        restriction.setId(1);
        restrictionBusinessService.add(restriction);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Exception on Update method
    //
    @Test(expected = BusinessException.class)
    public void testScenario02b_Update() throws BusinessException {
        Region region = new Region(regionTestName);
        Panel panel = new Panel(region, panelTestName);
        Restriction restriction = new Restriction(panel, restrictionTestName);
        restrictionBusinessService.update(restriction);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Exception on Remove method
    //
    @Test(expected = BusinessException.class)
    public void testScenario02c_Remove() throws BusinessException {
        Region region = new Region(regionTestName);
        Panel panel = new Panel(region, panelTestName);
        Restriction restriction = new Restriction(panel, restrictionTestName);
        restrictionBusinessService.remove(restriction);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Cleaning
    //
    @Test
    public void testScenario02z_Clean() throws BusinessException {
        List<Restriction> restrictionList1 = restrictionBusinessService.findByName(restrictionTestName);
        for(Restriction restriction : restrictionList1) {
            restrictionBusinessService.remove(restriction);
        }
        List<Restriction> restrictionList2 = restrictionBusinessService.findByName(restrictionTestName);
        assertEquals(new Integer(0), new Integer(restrictionList2.size()));

        List<Panel> panelList1 = panelBusinessService.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelBusinessService.remove(panel);
        }
        List<Panel> panelList2 = panelBusinessService.findByName(panelTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionBusinessService.findByName(regionTestName);
        for(Region region : regionList1) {
            regionBusinessService.remove(region);
        }
        List<Region> regionList2 = regionBusinessService.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
}
