import {Component, ViewEncapsulation} from '@angular/core';
import {ModalService} from '../../../services/modal.service/modal.service';

@Component({
  selector: 'modal-default',
  encapsulation: ViewEncapsulation.None,
  templateUrl: 'modal-default.component.html',
  styleUrls: ['modal-default.component.styl']
})
export class ModalDefaultComponent {
  constructor(
    public _modalService: ModalService
  ) {}
}
