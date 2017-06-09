import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {CommonService} from '../../../services/common.service/common.service';

@Component({
  selector: 'f-services',
  templateUrl: './f-services.component.html',
  styleUrls: ['./f-services.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FServicesComponent implements OnInit {
  submitted: boolean = false;
  constructor(public accountService: AccountService) {
  }

  ngOnInit() {
    if (this.accountService.activeServices.length) {
      return;
    }
    this.accountService.getAccountServices();
  }

  toggleService(data: {id: number, name: string; checked: boolean}) {
    data.checked = !data.checked;

    let pos = this.accountService.activeServices.indexOf(data.id);

    if (pos !== -1) {
      this.accountService.activeServices.splice(pos, 1);
    } else {
      this.accountService.activeServices.push(data.id);
    }
  }

  changeServices() {
    this.accountService.setAccountServices(this.accountService.activeServices)
      .subscribe(() => {
        console.log('services are updated');
        this.submitted = false;
      });

    this.submitted = true;
  }
}
