import { Injectable } from '@angular/core';

import {EquipmentModel} from './equipment.interface';
import {RequestService} from '../request.service/request.service';

@Injectable()
export class PilotsService {

  constructor(
    private requestService: RequestService
  ) {
  }

  savePilotEquipments(equipments: EquipmentModel[] = []) { // TODO: do we need this endpoint?
    return this.requestService.fetch('post', '/equipments', equipments);
  }

  updatePilotEquipments(equipments: EquipmentModel[] = []) {
    return this.requestService.fetch('put', '/equipments', equipments);
  }
}
