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
import com.drones4hire.dronesapp.dbaccess.dao.mysql.ProfileMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.StateMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.utils.DataGenerator;
import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.Profile;
import com.drones4hire.dronesapp.models.db.users.User;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class ProfileMapperTest extends AbstractTestNGSpringContextTests {

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
            setPostcode(1);
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
    
    private static final Profile PROFILE = new Profile() {
	   	 private static final long serialVersionUID = 1L;
	     {
	    	 setTagline("tagline");
	    	 setWebURL("url");
	    	 setBio("bio");
	    	 setCompanyLogoURL("logo url");
	    	 setCoverPhotoURL("photo url");
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
    private ProfileMapper profileMapper;
    
    @Test(enabled = ENABLED)
    public void testCreateProfile()
    {
        countryMapper.createCountry(COUNTRY);
        stateMapper.createState(STATE);
        locationMapper.createLocation(LOCATION);
        userMapper.createUser(USER);
        PROFILE.setUserId(USER.getId());
        profileMapper.createProfile(PROFILE);
        assertNotEquals(PROFILE.getId(), 0, "Profile ID must be set up by autogenerated keys");
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testCreateProfile"})
    public void testGetProfileById()
    {
    	Profile profile = profileMapper.getProfileById(PROFILE.getId());
        check(profile);
    }
    
    @Test(enabled = ENABLED, dependsOnMethods = {"testGetProfileById"})
    public void testGetProfileByUserId()
    {
    	Profile profile = profileMapper.getProfileByUserId(PROFILE.getId());
        check(profile);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetProfileByUserId"})
    public void testGetAllProfiles()
    {
        List<Profile> profiles = profileMapper.getAllProfiles();
        check(profiles.get(profiles.size() - 1));
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetAllProfiles"})
    public void testUpdateProfile()
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
        PROFILE.setUserId(USER.getId());
        PROFILE.setTagline("tagline2");
        PROFILE.setWebURL("url2");
        PROFILE.setBio("bio2");
        PROFILE.setCompanyLogoURL("logo url2");
        PROFILE.setCoverPhotoURL("photo url2");
        profileMapper.updateProfile(PROFILE);
        Profile profile = profileMapper.getProfileById(PROFILE.getId());
        check(profile);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testUpdateProfile"})
    public void testDeleteProfile()
    {
    	profileMapper.deleteProfile(PROFILE.getId());
        assertNull(profileMapper.getProfileById(PROFILE.getId()));
    }

    private void check(Profile profile)
    {
        assertEquals(profile.getId(), PROFILE.getId(), "Profile id must match");
        assertEquals(profile.getUserId(), PROFILE.getUserId(), "Profile user id must match");
        assertEquals(profile.getTagline(), PROFILE.getTagline(), "Profile tagline must match");
        assertEquals(profile.getWebURL(), PROFILE.getWebURL(), "Profile webUrl must match");
        assertEquals(profile.getBio(), PROFILE.getBio(), "Profile BIO must match");
        assertEquals(profile.getCompanyLogoURL(), PROFILE.getCompanyLogoURL(), "Profile logo url must match");
        assertEquals(profile.getCoverPhotoURL(), PROFILE.getCoverPhotoURL(), "Profile cover url must match");
    }
}
