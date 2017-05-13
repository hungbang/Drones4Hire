package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.commons.Budget;
import java.util.List;

public interface BudgetMapper {

    void createBudget(Budget budget);

    Budget getBudgetById(long id);

    List<Budget> getAllBudgets();

    void updateBudget(Budget budget);

    void deleteBudget(long id);
}
