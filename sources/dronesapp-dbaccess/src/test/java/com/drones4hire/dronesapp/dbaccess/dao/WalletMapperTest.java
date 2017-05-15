package com.drones4hire.dronesapp.dbaccess.dao;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.CountryMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.StateMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.UserMapper;
import com.drones4hire.dronesapp.dbaccess.dao.mysql.WalletMapper;
import com.drones4hire.dronesapp.dbaccess.utils.DataGenerator;
import com.drones4hire.dronesapp.models.db.commons.*;
import com.drones4hire.dronesapp.models.db.payments.Wallet;
import com.drones4hire.dronesapp.models.db.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import java.math.BigDecimal;
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
    private static final boolean ENABLED = true;

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

    @Test(enabled = ENABLED)
    public void testCreateWallet()
    {
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
