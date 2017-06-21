package com.drones4hire.dronesapp.ws.controllers;

import com.drones4hire.dronesapp.models.db.users.PilotEquipment;
import com.drones4hire.dronesapp.models.dto.PilotEquipmentDTO;
import com.drones4hire.dronesapp.services.exceptions.ForbiddenOperationException;
import com.drones4hire.dronesapp.services.services.PilotEquipmentService;
import com.drones4hire.dronesapp.ws.swagger.annotations.ResponseStatusDetails;
import io.swagger.annotations.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Api(value = "Pilot equipment API")
@RequestMapping("api/v1/equipments")
public class PilotEquipmentController extends AbstractController
{

	@Autowired
	private PilotEquipmentService pilotEquipmentService;

	@Autowired
	private Mapper mapper;

	@ResponseStatusDetails
	@ApiOperation(value = "Create pilot equipments", nickname = "createPilotEquipments", code = 201, httpMethod = "POST", response = List.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.CREATED)
	@Secured({ "ROLE_PILOT" })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PilotEquipmentDTO> createPilotEquipments(@Valid @RequestBody List<PilotEquipmentDTO> pe)
	{
		List<PilotEquipment> pilotEquipments = new ArrayList<>();
		List<PilotEquipmentDTO> pilotEquipmentDTOs = new ArrayList<>();
		PilotEquipment currentPilotEquipment = null;
		for(PilotEquipmentDTO pilotEquipmentDTO : pe)
		{
			currentPilotEquipment = mapper.map(pilotEquipmentDTO, PilotEquipment.class);
			currentPilotEquipment.setUserId(getPrincipal().getId());
			pilotEquipments.add(currentPilotEquipment);
		}
		pilotEquipments = pilotEquipmentService.createPilotEquipments(pilotEquipments);
		for(PilotEquipment pilotEquipment : pilotEquipments)
		{
			pilotEquipmentDTOs.add(mapper.map(pilotEquipment, PilotEquipmentDTO.class));
		}
		return pilotEquipmentDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get pilot equipment by id", nickname = "getPilotEquipmentById", code = 200, httpMethod = "GET", response = PilotEquipmentDTO.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody PilotEquipmentDTO getPilotEquipmentById(
			@ApiParam(value = "Id of the pilot equipment", required = true) @PathVariable(value = "id") long id)
	{
		return mapper.map(pilotEquipmentService.getPilotEquipmentById(id), PilotEquipmentDTO.class);
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Get all pilot equipments", nickname = "getAllPilotEquipments", code = 200, httpMethod = "GET", response = List.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PilotEquipmentDTO> getAllPilotEquipments()
	{
		List<PilotEquipment> pilotEquipments = pilotEquipmentService.getAllPilotEquipments();
		List<PilotEquipmentDTO> pilotEquipmentDTOs = new ArrayList<>();
		for(PilotEquipment pilotEquipment : pilotEquipments)
		{
			pilotEquipmentDTOs.add(mapper.map(pilotEquipment, PilotEquipmentDTO.class));
		}
		return pilotEquipmentDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Update pilot equipment", nickname = "updatePilotEquipment", code = 200, httpMethod = "PUT", response = PilotEquipmentDTO.class)
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.OK)
	@Secured({ "ROLE_PILOT" })
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PilotEquipmentDTO> updatePilotEquipment(@Valid @RequestBody List<PilotEquipmentDTO> pe)
			throws ForbiddenOperationException
	{
		List<PilotEquipment> pilotEquipments = new ArrayList<>();
		List<PilotEquipmentDTO> pilotEquipmentDTOs = new ArrayList<>();
		PilotEquipment currentPilotEquipment = null;
		for(PilotEquipmentDTO pilotEquipmentDTO : pe)
		{
			currentPilotEquipment = mapper.map(pilotEquipmentDTO, PilotEquipment.class);
			currentPilotEquipment.setUserId(getPrincipal().getId());
			pilotEquipments.add(currentPilotEquipment);
		}
		pilotEquipmentService.deletePilotEquipmentsByPilotId(getPrincipal().getId());
		pilotEquipments = pilotEquipmentService.createPilotEquipments(pilotEquipments);
		for(PilotEquipment pilotEquipment : pilotEquipments)
		{
			pilotEquipmentDTOs.add(mapper.map(pilotEquipment, PilotEquipmentDTO.class));
		}
		return pilotEquipmentDTOs;
	}

	@ResponseStatusDetails
	@ApiOperation(value = "Delete pilot equipment by id", nickname = "deletePilotEquipmentById", code = 204, httpMethod = "DELETE")
	@ApiImplicitParams(
			{ @ApiImplicitParam(name = "Authorization", paramType = "header") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Secured({ "ROLE_PILOT" })
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePilotEquipmentById(
			@ApiParam(value = "Id of the pilot equipment", required = true) @PathVariable(value = "id") long id)
			throws ForbiddenOperationException
	{
		PilotEquipment pilotEquipment = pilotEquipmentService.getPilotEquipmentById(id);
		checkPrincipalPermissions(pilotEquipment.getUserId());
		pilotEquipmentService.deletePilotEquipment(id);
	}
}
