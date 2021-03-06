package com.drones4hire.dronesapp.dbaccess.dao;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.*;
import com.drones4hire.dronesapp.dbaccess.utils.DataGenerator;
import com.drones4hire.dronesapp.models.db.commons.*;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;

@Test
@ContextConfiguration("classpath:com/drones4hire/dronesapp/dbaccess/dbaccess-test.xml")
public class WalletMapperTest extends AbstractTestNGSpringContextTests {

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
            setCode("c1" + DataGenerator.generateNumber(10000));
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

    private static final Group GROUP = new Group() {

        private static final long serialVersionUID = 1L;
        {
            setName("n1" + DataGenerator.generateNumber(10000));
            setRole(Role.ROLE_CLIENT);
        }
    };

    private static final User USER = new User() {
        private static final long serialVersionUID = 1L;
        {
            setUsername("userName" + DataGenerator.generateNumber(10000));
            setEmail("email" + DataGenerator.generateNumber(10000));
            setPassword("password");
            setFirstName("firstName");
            setLastName("lastName");
            setPhotoURL("url");
            setIntroduction("intro");
            setSummary("summary");
            setEnabled(Boolean.TRUE);
            setLocation(LOCATION);
            setGroups(new ArrayList<Group>());
            setConfirmed(true);
            getGroups().add(GROUP);
        }
    };

    private static final Wallet WALLET = new Wallet() {

        private static final long serialVersionUID = 1L;
        {
            setUserId(1L);
            setBalance(new BigDecimal(10.00D));
            setCurrency(Currency.USD);
            setPaymentToken("t1");
        }
    };

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private StateMapper stateMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;

    @Test(enabled = ENABLED)
    public void testCreateWallet()
    {
        groupMapper.createGroup(GROUP);
        countryMapper.createCountry(COUNTRY);
        stateMapper.createState(STATE);
        locationMapper.createLocation(LOCATION);
        userMapper.createUser(USER);
        WALLET.setUserId(USER.getId());
        walletMapper.createWallet(WALLET);
        assertNotEquals(WALLET.getId(), 0, "Wallet ID must be set up by autogenerated keys");
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testCreateWallet"})
    public void testGetWalletById()
    {
        Wallet wallet = walletMapper.getWalletById(WALLET.getId());
        check(wallet);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetWalletById"})
    public void testGetAllWallets()
    {
        List<Wallet> wallets = walletMapper.getAllWallets();
        check(wallets.get(wallets.size() - 1));
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testGetAllWallets"})
    public void testUpdateWallet()
    {
        WALLET.setUserId(2L);
        WALLET.setBalance(new BigDecimal(20.00D));
        WALLET.setCurrency(Currency.EUR);
        WALLET.setPaymentToken("t2");
        walletMapper.updateWallet(WALLET);
        Wallet wallet = walletMapper.getWalletById(WALLET.getId());
        check(wallet);
    }

    @Test(enabled = ENABLED, dependsOnMethods = {"testUpdateWallet"})
    public void testDeleteWallet()
    {
        walletMapper.deleteWallet(WALLET.getId());
        assertNull(walletMapper.getWalletById(WALLET.getId()));
    }

    private void check(Wallet wallet)
    {
        assertEquals(wallet.getId(), WALLET.getId(), "Wallet id must match");
        assertEquals(wallet.getUserId(), WALLET.getUserId(), "Wallet user id must match");
        assertEquals(wallet.getBalance().compareTo(WALLET.getBalance()), 0, "Wallet balance must match");
        assertEquals(wallet.getCurrency(), WALLET.getCurrency(), "Wallet currency must match");
        assertEquals(wallet.getPaymentToken(), WALLET.getPaymentToken(), "Wallet payment token must match");
    }
}
