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
 * RegionBusinessService - Unit Test
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = SpringConfig.class)
public class RegionBusinessServiceTest {
    
    @Autowired
    private RegionBusinessService regionBusinessService;
    
    @Autowired
    private PanelBusinessService panelBusinessService;
    
    private String regionTestName = "RegionBusinessServiceTest-Region";
    private String panelTestName = "RegionBusinessServiceTest-Panel";
    
    public RegionBusinessServiceTest() {
    }

    ////////////////////////////////////////////////////////////////////////////
    // Scenario 01 (Happy Path)
    ////////////////////////////////////////////////////////////////////////////

    //
    // Scenario 01 - Start
    //
    @Test
    public void testScenario01a_Start() throws BusinessException {
        List<Region> regionList1 = regionBusinessService.findByName(regionTestName);
        for(Region region : regionList1) {
            regionBusinessService.remove(region);
        }
        List<Region> regionList2 = regionBusinessService.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
        
        List<Region> result = regionBusinessService.findAll();
        assertNotNull(result);
    }
    
    //
    // Scenario 01 - Insert
    //
    @Test
    public void testScenario01b_Insert() throws BusinessException {
        Region region = new Region(regionTestName);
        regionBusinessService.add(region);
        assertNotEquals(new Integer(0), new Integer(region.getId()));
    }
    
    //
    // Scenario 01 - FindByName
    //
    @Test
    public void testScenario01c_FindByName() {
        List<Region> regionList = regionBusinessService.findByName(regionTestName);
        assertTrue(regionList.size()>0);
    }
    
    //
    // Scenario 01 - FindById
    //
    @Test
    public void testScenario01d_FindById() {
        List<Region> regionList = regionBusinessService.findByName(regionTestName);
        assertTrue(regionList.size()>0);
        for(Region region : regionList) {
            Region check = regionBusinessService.findById(region.getId());
            assertNotNull(check);
            assertEquals(check,region);
            assertTrue(check.getPanels().size()>=0);
        }
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01e_Update() throws BusinessException {
        List<Region> regionList = regionBusinessService.findByName(regionTestName);
        assertTrue(regionList.size()>0);
        for(Region region : regionList) {
            region.setName(region.getName()+"X");
            regionBusinessService.update(region);
            Region check = regionBusinessService.findById(region.getId());
            assertNotNull(check);
            assertEquals(check,region);
            assertTrue(check.getName().endsWith("X"));
        }
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01f_Delete() throws BusinessException {
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
        region.setId(1);
        regionBusinessService.add(region);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Exception on Update method
    //
    @Test(expected = BusinessException.class)
    public void testScenario02b_Update() throws BusinessException {
        Region region = new Region(regionTestName);
        regionBusinessService.update(region);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Exception on Remove method
    //
    @Test(expected = BusinessException.class)
    public void testScenario02c_Remove() throws BusinessException {
        Region region = new Region(regionTestName);
        regionBusinessService.remove(region);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Relationship constraint
    //
    @Test(expected = BusinessException.class)
    public void testScenario02d_Remove() throws BusinessException {
        
        Region region = new Region(regionTestName);
        regionBusinessService.add(region);

        Panel panel = new Panel(region, panelTestName);
        panelBusinessService.add(panel);
        
        regionBusinessService.remove(region);
        assertTrue(false);
    }
    
    //
    // Scenario 02 - Cleaning
    //
    @Test
    public void testScenario02z_Clean() throws BusinessException {
        List<Panel> panelList1 = panelBusinessService.findByName(panelTestName);
        for(Panel panel : panelList1) {
            panelBusinessService.remove(panel);
        }
        List<Panel> panelList2 = panelBusinessService.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(panelList2.size()));
        
        List<Region> regionList1 = regionBusinessService.findByName(regionTestName);
        for(Region region : regionList1) {
            regionBusinessService.remove(region);
        }
        List<Region> regionList2 = regionBusinessService.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Scenario 03
    ////////////////////////////////////////////////////////////////////////////
    
    // TODO: REMARKS: add more scenarios !
    
}
