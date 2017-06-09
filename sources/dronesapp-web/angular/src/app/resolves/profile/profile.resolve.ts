import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {PilotsService} from '../../services/pilots.service/pilots.service';

@Injectable()
export class ProfileResolve implements Resolve<any> {

  constructor(
    private _pilotsService: PilotsService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const profile_id = route.params['profile_id'];

    return this._pilotsService.getPilotByID(profile_id);
  }
}
