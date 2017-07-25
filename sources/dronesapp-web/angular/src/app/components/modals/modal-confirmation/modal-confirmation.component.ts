import {Component, ElementRef, Input, OnInit, Renderer2, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'modal-confirmation',
  templateUrl: './modal-confirmation.component.html',
  styleUrls: ['./modal-confirmation.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ModalConfirmationComponent implements OnInit {
  @Input() title: string;
  @Input() message: string;
  @Input() confirm_btn_text: string;
  @Input() cancel_btn_text: string;
  @Input() confirm = (e) => {};

  constructor(
    private _el: ElementRef,
    private _renderer: Renderer2
  ) { }

  ngOnInit() {
    this._renderer.listen(this._el.nativeElement, 'click', ($event) => {
      $event.stopPropagation();
    })
  }

}
