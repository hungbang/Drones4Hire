package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.commons.Duration;
import java.util.List;

public interface DurationMapper {

    void createDuration(Duration duration);

    Duration getDurationById(long id);

    List<Duration> getAllDurations();

    void updateDuration(Duration duration);

    void deleteDuration(long id);
}
