package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.commons.State;
import java.util.List;

public interface StateMapper {

    void createState(State state);

    State getStateById(long id);

    List<State> getAllStates();

    void updateState(State state);

    void deleteState(long id);
}
