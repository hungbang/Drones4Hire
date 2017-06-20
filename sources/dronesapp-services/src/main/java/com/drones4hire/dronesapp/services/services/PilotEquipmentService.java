package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotEquipmentMapper;
import com.drones4hire.dronesapp.models.db.users.Group;
import com.drones4hire.dronesapp.models.db.users.PilotEquipment;
import com.drones4hire.dronesapp.models.db.users.User;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PilotEquipmentService
{

	@Autowired
	private PilotEquipmentMapper pilotEquipmentMapper;

	@Autowired
	private UserService userService;

	@Transactional(rollbackFor = Exception.class)
	public PilotEquipment createPilotEquipment(PilotEquipment pilotEquipment)
	{
		pilotEquipmentMapper.createPilotEquipment(pilotEquipment);
		return pilotEquipment;
	}

	@Transactional(rollbackFor = Exception.class)
	public List<PilotEquipment> createPilotEquipments(List<PilotEquipment> pilotEquipments)
	{
		pilotEquipmentMapper.createPilotEquipments(pilotEquipments);
		return pilotEquipments;
	}

	@Transactional(readOnly = true)
	public PilotEquipment getPilotEquipmentById(long id)
	{
		return pilotEquipmentMapper.getPilotEquipmentById(id);
	}

	@Transactional(readOnly = true)
	public List<PilotEquipment> getPilotEquipmentsByPilotId(long pilotId) throws ServiceException
	{
		User user = userService.getUserById(pilotId);
		if(! user.getRoles().contains(Group.Role.ROLE_PILOT))
		{
			throw new ForbiddenOperationException();
		}
		return pilotEquipmentMapper.getPilotEquipmentsByPilotId(pilotId);
	}

	@Transactional(readOnly = true)
	public List<PilotEquipment> getAllPilotEquipments()
	{
		return pilotEquipmentMapper.getAllPilotEquipments();
	}

	@Transactional(rollbackFor = Exception.class)
	public PilotEquipment updatePilotEquipment(PilotEquipment pilotEquipment)
	{
		pilotEquipmentMapper.updatePilotEquipment(pilotEquipment);
		return pilotEquipment;
	}

	@Transactional(rollbackFor = Exception.class)
	public void deletePilotEquipment(long id)
	{
		pilotEquipmentMapper.deletePilotEquipment(id);
	}
}
