package cc_test.cc_dao;

import cc_test.cc_dao.model.Region;
import cc_test.config.SpringConfig;

import java.util.Arrays;
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
 * RegionDAO Test
 * @author Fernando
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = SpringConfig.class)
public class RegionDAOTest {

    @Autowired
    ApplicationContext ctx;
    
    @Autowired
    private RegionDAO regionDAO;
    
    private String regionTestName = "RegionDAOTest";
    
    public RegionDAOTest() {
    }
    
    //
    // testDummy
    //
    @Test
    public void testDummy() {
        System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));
        assertNotNull(ctx);
        assertNotNull(regionDAO);
    }
    
    //
    // Scenario 01 - Start
    //
    @Test
    public void testScenario01a_Start() {
        //ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //RegionDAO regionDAO = context.getBean("regionDAO");
        
        List<Region> regionList1 = regionDAO.findByName(regionTestName);
        for(Region region : regionList1) {
            regionDAO.remove(region);
        }
        List<Region> regionList2 = regionDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
        
        List<Region> result = regionDAO.findAll();
        assertNotNull(result);
    }
    
    //
    // Scenario 01 - Insert
    //
    @Test
    public void testScenario01b_Insert() {
        Region region = new Region(regionTestName);
        regionDAO.save(region);
        assertNotEquals(new Integer(0), new Integer(region.getId()));
    }
    
    //
    // Scenario 01 - FindByName
    //
    @Test
    public void testScenario01c_FindByName() {
        List<Region> regionList = regionDAO.findByName(regionTestName);
        assertTrue(regionList.size()>0);
    }
    
    //
    // Scenario 01 - FindById
    //
    @Test
    public void testScenario01d_FindById() {
        List<Region> regionList = regionDAO.findByName(regionTestName);
        assertTrue(regionList.size()>0);
        for(Region region : regionList) {
            Region check = regionDAO.findById(region.getId());
            assertNotNull(check);
            assertEquals(check,region);
            assertTrue(check.getPanels().size()>=0);
        }
    }
    
    //
    // Scenario 01 - Update
    //
    @Test
    public void testScenario01e_Update() {
        List<Region> regionList = regionDAO.findByName(regionTestName);
        assertTrue(regionList.size()>0);
        for(Region region : regionList) {
            region.setName(region.getName()+"X");
            regionDAO.save(region);
            Region check = regionDAO.findById(region.getId());
            assertNotNull(check);
            assertEquals(check,region);
            assertTrue(check.getName().endsWith("X"));
        }
    }
    
    //
    // Scenario 01 - Delete
    //
    @Test
    public void testScenario01f_Delete() {
        List<Region> regionList1 = regionDAO.findByName(regionTestName);
        for(Region region : regionList1) {
            regionDAO.remove(region);
        }
        List<Region> regionList2 = regionDAO.findByName(regionTestName);
        assertEquals(new Integer(0), new Integer(regionList2.size()));
    }
    
}
