package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.users.Group;
import java.util.List;

public interface GroupMapper {

    void createGroup(Group group);

    Group getGroupById(long id);

    List<Group> getAllGroups();

    void updateGroup(Group group);

    void deleteGroup(long id);
}
