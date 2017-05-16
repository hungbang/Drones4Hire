package com.drones4hire.dronesapp.services.services;

import com.drones4hire.dronesapp.dbaccess.dao.mysql.PilotLicenseMapper;
import com.drones4hire.dronesapp.models.db.users.PilotLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PilotLicenseService {

    @Autowired
    private PilotLicenseMapper pilotLicenseMapper;

    @Transactional(rollbackFor = Exception.class)
    public PilotLicense createPilotLicense(PilotLicense pilotLicense) {
        pilotLicenseMapper.createPilotLicense(pilotLicense);
        return pilotLicense;
    }

    @Transactional(readOnly = true)
    public PilotLicense getPilotLicenseById(long id) {
        return pilotLicenseMapper.getPilotLicenseById(id);
    }

    @Transactional(readOnly = true)
    public List<PilotLicense> getAllPilotLicenses() {
        return pilotLicenseMapper.getAllPilotLicenses();
    }

    @Transactional(rollbackFor = Exception.class)
    public PilotLicense updatePilotLicense(PilotLicense pilotLicense) {
        pilotLicenseMapper.updatePilotLicense(pilotLicense);
        return pilotLicense;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletePilotLicense(long id) {
        pilotLicenseMapper.deletePilotLicense(id);
    }
}
