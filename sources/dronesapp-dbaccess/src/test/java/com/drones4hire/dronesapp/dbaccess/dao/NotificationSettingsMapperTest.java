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
import com.drones4hire.dronesapp.dbaccess.dao.mysql.NotificationSettingsMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.StateMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.utils.DataGenerator;
import com.drones4hire.dronesapp.models.db.commons.Coordinates;
import com.drones4hire.dronesapp.models.db.commons.Country;
import com.drones4hire.dronesapp.models.db.commons.Location;
import com.drones4hire.dronesapp.models.db.commons.State;
import com.drones4hire.dronesapp.models.db.settings.NotificationSettings;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class NotificationSettingsMapperTest extends AbstractTestNGSpringContextTests {

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
    
    private static final NotificationSettings SETTINGS = new NotificationSettings() {
	   	 private static final long serialVersionUID = 1L;
	     {
	    	 setPlainEmail(Boolean.TRUE);
	    	 setBidPlaced(Boolean.TRUE);
	    	 setPaymentReceived(Boolean.TRUE);
	    	 setProjectUpdate(Boolean.TRUE);
	    	 setStaff(Boolean.TRUE);
	    	 setDronesNews(Boolean.TRUE);
	    	 setProjectAward(Boolean.TRUE);
	    	 setMarketing(Boolean.TRUE);
	    	 setDeals(Boolean.TRUE);
	    	 setMonthlyNews(Boolean.TRUE);
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
    private NotificationSettingsMapper settingsMapper;
    
    @Test(enabled = ENABLED)
    public void createNotificationSettings()
    {
        countryMapper.createCountry(COUNTRY);
        stateMapper.createState(STATE);
        locationMapper.createLocation(LOCATION);
        userMapper.createUser(USER);
        SETTINGS.setUserId(USER.getId());
        settingsMapper.createNotificationSettings(SETTINGS);
        assertNotEquals(SETTINGS.getId(), 0, "Settings ID must be set up by autogenerated keys");
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"createNotificationSettings"})
    public void testGetNotificationSettingsById()
    {
    	NotificationSettings settings = settingsMapper.getNotificationSettingsById(SETTINGS.getId());
        check(settings);
    }
    
    @Test(enabled = ENABLED, dependsOnMethods = {"testGetNotificationSettingsById"})
    public void testGetNotificationSettingsByUserId()
    {
    	NotificationSettings settings = settingsMapper.getNotificationSettingsByUserId(USER.getId());
        check(settings);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetNotificationSettingsByUserId"})
    public void testGetAllNotificationSettings()
    {
        List<NotificationSettings> settings = settingsMapper.getAllNotificationSettings();
        check(settings.get(settings.size() - 1));
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetAllNotificationSettings"})
    public void testUpdateNotificationSettings()
    {
        COUNTRY.setName("n2");
        countryMapper.createCountry(COUNTRY);
        STATE.setName("n2");
        stateMapper.createState(STATE);
        LOCATION.setAddress("a2");
        LOCATION.setCoordinates(new Coordinates(3.3, 4.4));
        LOCATION.setCountry(COUNTRY);
        LOCATION.setState(STATE);
        LOCATION.setCity("c2");
        LOCATION.setPostcode("2");
        locationMapper.createLocation(LOCATION);
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
        SETTINGS.setPlainEmail(Boolean.FALSE);
        SETTINGS.setBidPlaced(Boolean.FALSE);
        SETTINGS.setPaymentReceived(Boolean.FALSE);
        SETTINGS.setProjectUpdate(Boolean.FALSE);
        SETTINGS.setStaff(Boolean.FALSE);
        SETTINGS.setDronesNews(Boolean.FALSE);
        SETTINGS.setProjectAward(Boolean.FALSE);
        SETTINGS.setMarketing(Boolean.FALSE);
        SETTINGS.setDeals(Boolean.FALSE);
        SETTINGS.setMonthlyNews(Boolean.FALSE);
        SETTINGS.setUserId(USER.getId());
        settingsMapper.updateNotificationSettings(SETTINGS);
        NotificationSettings settings = settingsMapper.getNotificationSettingsById(SETTINGS.getId());
        check(settings);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testUpdateNotificationSettings"})
    public void testDeleteNotificationSettings()
    {
    	settingsMapper.deleteNotificationSettings(SETTINGS.getId());
        assertNull(settingsMapper.getNotificationSettingsById(SETTINGS.getId()));
    }

    private void check(NotificationSettings settings)
    {
        assertEquals(settings.getId(), SETTINGS.getId(), "Settings id must match");
        assertEquals(settings.isPlainEmail(), SETTINGS.isPlainEmail(), "Settings plain email must match");
        assertEquals(settings.isBidPlaced(), SETTINGS.isBidPlaced(), "Settings bid placed must match");
        assertEquals(settings.isPaymentReceived(), SETTINGS.isPaymentReceived(), "Settings payment received must match");
        assertEquals(settings.isProjectUpdate(), SETTINGS.isProjectUpdate(), "Settings project update must match");
        assertEquals(settings.isStaff(), SETTINGS.isStaff(), "Settings staff must match");
        assertEquals(settings.isDronesNews(), SETTINGS.isDronesNews(), "Settings drones news must match");
        assertEquals(settings.isProjectAward(), SETTINGS.isProjectAward(), "Settings project award must match");
        assertEquals(settings.isMarketing(), SETTINGS.isMarketing(), "Settings marketing must match");
        assertEquals(settings.isDeals(), SETTINGS.isDeals(), "Settings deals must match");
        assertEquals(settings.isMonthlyNews(), SETTINGS.isMonthlyNews(), "Settings monthly news must match");
        assertEquals(settings.getUserId(), SETTINGS.getUserId(), "Settings user id must match");
    }
}
