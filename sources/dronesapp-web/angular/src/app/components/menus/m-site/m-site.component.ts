import {Component, Input, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'm-site',
  templateUrl: './m-site.component.html',
  styleUrls: ['./m-site.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class MSiteComponent {
  @Input() menu: any[];

  constructor() {

  }
}
