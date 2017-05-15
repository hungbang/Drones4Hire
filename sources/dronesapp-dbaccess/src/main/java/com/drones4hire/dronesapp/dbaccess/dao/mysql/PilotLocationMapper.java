package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.users.PilotLocation;
import java.util.List;

public interface PilotLocationMapper {

    void createPilotLocation(PilotLocation pilotLocation);

    PilotLocation getPilotLocationById(long id);

    List<PilotLocation> getAllPilotLocations();

    void updatePilotLocation(PilotLocation pilotLocation);

    void deletePilotLocation(long id);
}
