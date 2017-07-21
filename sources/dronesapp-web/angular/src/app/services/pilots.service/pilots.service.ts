import { Injectable } from '@angular/core';

import {EquipmentModel} from './equipment.interface';
import {RequestService} from '../request.service/request.service';

@Injectable()
export class PilotsService {

  constructor(
    private requestService: RequestService
  ) {
  }

  updatePilotEquipments(equipments: EquipmentModel[] = []) {
    return this.requestService.fetch('put', '/equipments', equipments);
  }
}
