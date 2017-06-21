package com.drones4hire.dronesapp.dbaccess.dao.mysql;

import com.drones4hire.dronesapp.models.db.users.PilotEquipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PilotEquipmentMapper
{

	void createPilotEquipment(PilotEquipment pilotEquipment);

	void createPilotEquipments(@Param("pilotEquipments") List<PilotEquipment> pilotEquipments);

	PilotEquipment getPilotEquipmentById(long id);

	List<PilotEquipment> getPilotEquipmentsByPilotId(long pilotId);

	List<PilotEquipment> getAllPilotEquipments();

	void updatePilotEquipment(PilotEquipment pilotEquipment);

	void deletePilotEquipment(long id);

	void deletePilotEquipmentsByPilotId(long pilotId);
}
