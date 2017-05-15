package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.payments.Wallet;
import java.util.List;

public interface WalletMapper {

    void createWallet(Wallet wallet);

    Wallet getWalletById(long id);

    List<Wallet> getAllWallets();

    void updateWallet(Wallet wallet);

    void deleteWallet(long id);
}
