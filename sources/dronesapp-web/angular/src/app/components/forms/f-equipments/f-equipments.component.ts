import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import {PublicService} from '../../../services/public.service/public.service';
import {EquipmentModel} from '../../../services/pilots.service/equipment.interface';
import {PilotsService} from '../../../services/pilots.service/pilots.service';

@Component({
  selector: 'f-equipments',
  templateUrl: './f-equipments.component.html',
  styleUrls: ['./f-equipments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FEquipmentsComponent implements OnInit {
  drones: EquipmentModel[] = [];
  cameras: EquipmentModel[] = [];

  constructor(
    private accountService: AccountService,
    private publicService: PublicService,
    private pilotsService: PilotsService
  ) { }

  ngOnInit() {
    let equipments = [];

    this.publicService.getPublicAccountEquipments(this.userId)
      .subscribe(
        res => {
          equipments = res;
          if (equipments.length) {
            this.fetchEquipments(equipments);
          }
        },
        err => {
          console.log('get account equipments error:', err);
        }
      );

    if (!equipments.length) {
      this.addDrone();
      this.addCamera();
    }
  }

  addDrone() {
    const drone = {
      userId: this.userId,
      name: '',
      type: 'DRONE'
    };

    this.drones.push(drone);
  }

  addCamera() {
    const camera = {
      userId: this.userId,
      name: '',
      type: 'CAMERA'
    };

    this.cameras.push(camera);
  }

  fetchEquipments(equipments) {
    this.drones = equipments.filter(el => el.type === 'DRONE');
    this.cameras = equipments.filter(el => el.type === 'CAMERA');
  }

  get userId() {
    return this.accountService.account.id;
  }

  private cleanData() {
    this.drones = this.drones.filter(el => el.name !== '');
    this.cameras = this.cameras.filter(el => el.name !== '');
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.cleanData();
    this.pilotsService.updatePilotEquipments([...this.drones, ...this.cameras]).subscribe(
      res => {
        // console.log('updated equipments:', res);
      },
      err => {
        console.log('update equipments error:', err);
      }
    );
  }
}
