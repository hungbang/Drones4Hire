package com.drones4hire.dronesapp.dbaccess.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CountryMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.LocationMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotLicenseMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.StateMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.utils.DataGenerator;
import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import com.drones4hire.dronesapp.models.db.users.User;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class PilotLicenseMapperTest extends AbstractTestNGSpringContextTests {

    /**
     * Turn this on to enable this test
     */
    private static final boolean ENABLED = false;

    private static final Country COUNTRY = new Country() {

        private static final long serialVersionUID = 1L;
        {
            setName("n1" + DataGenerator.generateNumber(10000));
        }
    };

    private static final State STATE = new State() {

        private static final long serialVersionUID = 1L;
        {
            setName("n1" + DataGenerator.generateNumber(10000));
        }
    };

    private static final Location LOCATION = new Location() {

        private static final long serialVersionUID = 1L;
        {
            setAddress("a1");
            setCoordinates(new Coordinates(1.1, 2.2));
            setCountry(COUNTRY);
            setState(STATE);
            setCity("c2");
            setPostcode("1");
        }
    };
    
    private static final User USER = new User() {
    	 private static final long serialVersionUID = 1L;
         {
        	setUsername("userName");
        	setEmail("email");
        	setPassword("password");
        	setFirstName("firstName");
        	setLastName("lastName");
        	setPhotoURL("url");
        	setIntroduction("intro");
        	setSummary("summary");
        	setEnabled(Boolean.TRUE);
            setLocation(LOCATION);
            setGroups(new ArrayList<Group>());
         }
    };
    
    private static final PilotLicense PILOT_LICENSE = new PilotLicense() {
	   	 private static final long serialVersionUID = 1L;
	     {
	    	 setInsuranceURL("insurance url");
	    	 setLicenseURL("license url");
	    	 setVerified(Boolean.TRUE);
	     }
   };

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private StateMapper stateMapper;
    
    @Autowired
    private LocationMapper locationMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PilotLicenseMapper pilotLicenseMapper;
    
    @Test(enabled = ENABLED)
    public void testCreatePilotLicense()
    {
        countryMapper.createCountry(COUNTRY);
        stateMapper.createState(STATE);
        locationMapper.createLocation(LOCATION);
        userMapper.createUser(USER);
        PILOT_LICENSE.setUserId(USER.getId());
        pilotLicenseMapper.createPilotLicense(PILOT_LICENSE);
        assertNotEquals(PILOT_LICENSE.getId(), 0, "Pilot License ID must be set up by autogenerated keys");
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testCreatePilotLicense"})
    public void testGetPilotLicenseById()
    {
    	PilotLicense license = pilotLicenseMapper.getPilotLicenseById(PILOT_LICENSE.getId());
        check(license);
    }
    
    @Test(enabled = ENABLED, dependsOnMethods = {"testGetPilotLicenseById"})
    public void testGetPilotLicenseByUserId()
    {
    	PilotLicense license = pilotLicenseMapper.getPilotLicenseByUserId(PILOT_LICENSE.getId());
        check(license);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetPilotLicenseByUserId"})
    public void testGetAllPilotLicenses()
    {
        List<PilotLicense> licenses = pilotLicenseMapper.getAllPilotLicenses();
        check(licenses.get(licenses.size() - 1));
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetAllPilotLicenses"})
    public void testUpdatePilotLicense()
    {
    	COUNTRY.setName("n2");
        countryMapper.createCountry(COUNTRY);
        USER.setUsername("userName2");
        USER.setEmail("email2");
        USER.setPassword("password2");
        USER.setFirstName("firstName2");
        USER.setLastName("lastName2");
        USER.setPhotoURL("url2");
        USER.setIntroduction("intro2");
        USER.setSummary("summary2");
        USER.setEnabled(Boolean.FALSE);
        USER.setLocation(LOCATION);
        USER.setGroups(new ArrayList<Group>());
        userMapper.createUser(USER);
        PILOT_LICENSE.setInsuranceURL("insurance url2");
        PILOT_LICENSE.setLicenseURL("license url2");
        PILOT_LICENSE.setVerified(Boolean.FALSE);
        pilotLicenseMapper.updatePilotLicense(PILOT_LICENSE);
        PilotLicense license = pilotLicenseMapper.getPilotLicenseById(PILOT_LICENSE.getId());
        check(license);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testUpdatePilotLicense"})
    public void testDeletePilotLicense()
    {
    	pilotLicenseMapper.deletePilotLicense(PILOT_LICENSE.getId());
        assertNull(pilotLicenseMapper.getPilotLicenseById(PILOT_LICENSE.getId()));
    }

    private void check(PilotLicense license)
    {
        assertEquals(license.getId(), PILOT_LICENSE.getId(), "License id must match");
        assertEquals(license.getUserId(), PILOT_LICENSE.getUserId(), "License user id must match");
        assertEquals(license.getLicenseURL(), PILOT_LICENSE.getLicenseURL(), "License url must match");
        assertEquals(license.getInsuranceURL(), PILOT_LICENSE.getInsuranceURL(), "License insurance must match");
        assertEquals(license.isVerified(), PILOT_LICENSE.isVerified(), "License verified must match");
    }
}
