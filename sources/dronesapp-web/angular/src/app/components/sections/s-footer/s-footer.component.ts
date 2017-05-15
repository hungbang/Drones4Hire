import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 's-footer',
  templateUrl: './s-footer.component.html',
  styleUrls: ['./s-footer.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FooterComponent {

  isNav: boolean = true;
  isSupport: boolean = true;
  isTouch: boolean = true;

  constructor() {}
}
