import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { PilotsService } from '../../services/pilots.service/pilots.service';

@Component({
  selector: 'profile-page',
  templateUrl: './profile.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./profile.component.styl']
})
export class ProfileComponent implements OnInit {
  profile: Object;
  constructor(
    public _pilotsService: PilotsService
  ) { }

  ngOnInit() {
    this.profile = this._pilotsService.selectedPilot;
  }

}
