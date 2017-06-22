import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {PublicService} from '../../services/public.service/public.service';


@Injectable()
export class ProfileEquipmentsResolve implements Resolve<any> {

  constructor(
    public publicService: PublicService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const user_id = route.params['user_id'];

    return this.publicService.getPublicAccountEquipments(user_id);
  }
}
