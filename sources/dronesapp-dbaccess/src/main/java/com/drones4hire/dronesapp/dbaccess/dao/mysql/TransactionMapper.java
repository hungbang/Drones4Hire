package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.search.TransactionSearchCriteria;
import com.drones4hire.dronesapp.models.db.payments.Transaction;
import java.util.List;

public interface TransactionMapper {

    void createTransaction(Transaction transaction);

    Transaction getTransactionById(long id);

    List<Transaction> getAllTransactions();

    List<Transaction> searchTransactions(TransactionSearchCriteria sc);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(long id);
}
