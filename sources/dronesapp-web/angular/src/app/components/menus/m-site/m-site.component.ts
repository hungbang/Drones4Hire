import {Component, EventEmitter, HostBinding, HostListener, Input, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'm-site',
  templateUrl: './m-site.component.html',
  styleUrls: ['./m-site.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class MSiteComponent {
  @Input() menu: any[];
  @Output() closeMenu = new EventEmitter();

  private openedMenus = [];
  private canClose = false;

  _isOpen: boolean;

  constructor() {

  }

  openSubMenuAction(item) {
    if (item.isOpen) {
      this.closeSubMenu(item);
      const index = this.openedMenus.indexOf(item);

      index >= 0 && this.openedMenus.splice(index, 1);
    } else {
      item.isOpen = true;
      this.canClose = false;
      this.openedMenus.push(item);
    }
  }

  closeSubMenu(item) {
    item.isOpen = false;
  }

  closeAllSubMenus() {
    this.openedMenus.forEach(this.closeSubMenu);
    this.openedMenus = [];
  }

  @HostListener('document:click', ['$event'])
  clickOut(event) {
    if (this.canClose) {
      this.closeAllSubMenus();
    }
    this.canClose = true;
  }

  @Input()
  @HostBinding('class._opened')
  set isOpen(value) {
    this._isOpen = value;

    this.closeAllSubMenus();
  }
  get isOpen() {
    return this._isOpen;
  }

  onCloseMenu() {
    this.closeMenu.emit()
  }
}
