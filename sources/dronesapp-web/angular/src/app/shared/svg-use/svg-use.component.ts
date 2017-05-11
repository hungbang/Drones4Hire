import {Component, Input, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'svg-use',
  encapsulation: ViewEncapsulation.None,
  templateUrl: 'svg-use.component.html',
  styleUrls: ['svg-use.component.styl']
})
export class SvgUseComponent {
  @Input() name: string;
  constructor(
  ) {}

  get baseUrl() {
    return window.location.href.replace(window.location.hash, '');
  }
}
