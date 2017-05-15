package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.payments.WithdrawRequest;
import java.util.List;

public interface WithdrawRequestMapper {

    void createWithdrawRequest(WithdrawRequest withdrawRequest);

    WithdrawRequest getWithdrawRequestById(long id);

    List<WithdrawRequest> getAllWithdrawRequests();

    void updateWithdrawRequest(WithdrawRequest withdrawRequest);

    void deleteWithdrawRequest(long id);
}
