import {Component, OnInit, ViewContainerRef, ViewEncapsulation} from '@angular/core';
import {ModalService} from "./services/modal.service/modal.service";
import {ModalConfirmationComponent} from "./components/modals/modal-confirmation/modal-confirmation.component";
import {ToastsManager} from 'ng2-toastr';

@Component({
  selector: 'app-root',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {
  isIndex: boolean = true;

  constructor(
    private modalService: ModalService,
    private toastr: ToastsManager,
    private vRef: ViewContainerRef
  ) {
    this.toastr.setRootViewContainerRef(this.vRef);
  }

  ngOnInit() {

    this.modalService.push({
      component: ModalConfirmationComponent,
      type: 'ModalConfirmationComponent',
      values: {
        title: '',
        message: 'Do you really want to release payments?',
        confirm_btn_text: 'Yes',
        cancel_btn_text: 'No',
        confirm: e => { this.confirmExit(e); }
      }
    });
  }

  confirmExit(event) {
    console.log(event);
    this.modalService.pop();
  }

  get modals() {
    return this.modalService.modals;
  }
}
