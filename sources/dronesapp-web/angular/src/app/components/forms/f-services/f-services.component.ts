import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-services',
  templateUrl: './f-services.component.html',
  styleUrls: ['./f-services.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FServicesComponent implements OnInit {
  constructor(
    public accountService: AccountService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
  }

  ngOnInit() {
  }

  changeServices() {
    this.progressbarService.start();
    this.accountService.setAccountServices(this.accountService.activeServices)
      .subscribe(
        () => {
          this.progressbarService.done();
          console.log('services are updated');
          this.toastrService.showSuccess('Saved')
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 400) {
            const body = err.json();

            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
        );
  }
}
