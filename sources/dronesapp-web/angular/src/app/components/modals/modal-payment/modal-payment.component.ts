import {Component, ElementRef, Input, OnInit, Renderer2, ViewEncapsulation} from '@angular/core';
import * as braintree from 'braintree-web-drop-in';

import {ModalService} from '../../../services/modal.service/modal.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'modal-payment',
  templateUrl: './modal-payment.component.html',
  styleUrls: ['./modal-payment.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ModalPaymentComponent implements OnInit {
  @Input() title: string;
  @Input() message: string;
  @Input() paymentFn: Function = () => {};
  @Input() clientToken: string = '';
  dropInstance: any = null;
  isLoading: boolean = true;

  constructor(
    private _el: ElementRef,
    private _renderer: Renderer2,
    private _modalService: ModalService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
    this._renderer.listen(this._el.nativeElement, 'click', ($event) => {
      $event.stopPropagation();
    });

    this.initBrainTree(this.clientToken);
  }

  initBrainTree(token) {
    braintree.create({
        authorization: token,
        selector: '#dropin-container'
      },
      (err, client) => {
        if (err) {
          console.log(err);
          let message: string = '';
          if (err._braintreeWebError) {
            message = err._braintreeWebError.message;
          } else {
            message = err.message;
          }
          this.toastrService.showError(message);
          return;
        }

        // console.log(client);
        this.isLoading = false;
        this.dropInstance = client;
      });
  }

  onSubmit(e, form) {
    e.preventDefault();

    if (!this.dropInstance) {
      return;
    }

    this.dropInstance.requestPaymentMethod((err, payload) => {
      if (err) {
        console.log(err);
        let message: string = '';
        if (err._braintreeWebError) {
          message = err._braintreeWebError.message;
        } else {
          message = err.message;
        }
        this.toastrService.showError(message);
        return;
      }

      // console.log(payload);
      this.paymentFn(payload.nonce);
    });
  }

  close() {
    this._modalService.pop();
  }
}
