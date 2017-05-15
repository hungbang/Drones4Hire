import { Component, HostListener, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 's-header',
  templateUrl: './s-header.component.html',
  styleUrls: ['./s-header.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent {

  isScroll = false;

  constructor() {}

  @HostListener('window:scroll') onScroll() {
    this.isScroll = window.pageYOffset > 80;
  }
}
