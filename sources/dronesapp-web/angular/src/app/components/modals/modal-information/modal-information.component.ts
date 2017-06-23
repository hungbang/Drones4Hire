import {Component, ElementRef, Input, OnInit, Renderer2, ViewEncapsulation} from '@angular/core';
import {ModalService} from "../../../services/modal.service/modal.service";

@Component({
  selector: 'modal-information',
  templateUrl: './modal-information.component.html',
  styleUrls: ['./modal-information.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ModalInformationComponent implements OnInit {
  @Input() title: string;
  @Input() message: string;

  constructor(
    private _el: ElementRef,
    private _renderer: Renderer2,
    private _modalService: ModalService
  ) { }

  ngOnInit() {
    this._renderer.listen(this._el.nativeElement, 'click', ($event) => {
      $event.stopPropagation();
    })
  }

  close() {
    this._modalService.pop();
  }
}
