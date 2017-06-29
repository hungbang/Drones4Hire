import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

import { AccountService } from '../../../services/account.service/account.service';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import {ModalInformationComponent} from '../../modals/modal-information/modal-information.component';
import {ModalService} from '../../../services/modal.service/modal.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 's-index',
  templateUrl: './s-index.component.html',
  styleUrls: ['./s-index.component.styl']
})
export class SIndexComponent implements OnInit {

  constructor(
    public _accountService: AccountService,
    public _authorizationService: AuthorizationService,
    private _router: Router,
    private _modalService: ModalService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
  }

  goToFindAJobIfAccessExist(e) {
    e.preventDefault();

    if (this._accountService.license.verified) {
      this._router.navigate(['/search']);
    } else {
      this._accountService.getAccountLicense().subscribe(
        res => {
          if (res.verified) {
            this._router.navigate(['/search']);
          } else {
            this._modalService.push({
              component: ModalInformationComponent,
              type: 'ModalInformationComponent',
              values: {
                title: '',
                message: 'Pilot: Your license and/or certificate were not verified yet. Please upload it on Account settings, to gain access to job board.'
              }
            });
          }
        },
        err => {
          console.log('get license error', err);
          this.toastrService.showError('Can\'t check you license status. Please try later.');
        }
      );
    }
  }

}
