import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import { AccountService } from '../../../services/account.service/account.service';
import {Router} from "@angular/router";
import {ModalInformationComponent} from "../../modals/modal-information/modal-information.component";
import {ModalService} from "../../../services/modal.service/modal.service";

@Component({
  selector: 'a-project',
  templateUrl: './a-project.component.html',
  styleUrls: ['./a-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class AProjectComponent implements OnInit {

  constructor(
    public _accountService: AccountService,
    public _authorizationService: AuthorizationService,
    private _router: Router,
    private _modalService: ModalService,
    ) { }

  ngOnInit() {
  }

  checkAccess(e) {
    e.preventDefault();

    this._router.navigate(['/project', 'manage', 'add']);

    if (!this._modalService) { // TODO: Do we still need this modal
      this._modalService.push({
        component: ModalInformationComponent,
        type: 'ModalInformationComponent',
        values: {
          title: 'Post a project',
          message: 'Please complete your billing information on Payment info, to get the possibility to post your project.'
        }
      });

      return;
    }

    return false;
  }

}
