package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.commons.Location;
import java.util.List;

public interface LocationMapper {

    void createLocation(Location location);

    Location getLocationById(long id);

    List<Location> getAllLocations();

    void updateLocation(Location location);

    void deleteLocation(long id);
}
