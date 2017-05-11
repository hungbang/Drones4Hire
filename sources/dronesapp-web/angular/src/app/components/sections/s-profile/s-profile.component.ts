import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';
import { PilotsService } from '../../../services/pilots.service/pilots.service';

@Component({
  selector: 's-profile',
  templateUrl: './s-profile.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./s-profile.component.styl']
})
export class SProfileComponent implements OnInit {
  @Input() profile;
  constructor(
    public _pilotsService: PilotsService
  ) { }

  ngOnInit() {
    this.profile = this._pilotsService.selectedPilot;
  }

}
