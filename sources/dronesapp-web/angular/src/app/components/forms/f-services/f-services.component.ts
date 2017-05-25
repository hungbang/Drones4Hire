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
  constructor(private _accountService: AccountService,
              public commonService: CommonService) {
  }

  ngOnInit() {
    this._accountService.getUserServices();
  }

  toggleService(data: {id: number, name: string; checked: boolean}) {
    data.checked = !data.checked;

    let pos = this._accountService.services.indexOf(data.id);

    if (pos !== -1) {
      this._accountService.services.splice(pos, 1);
    } else {
      this._accountService.services.push(data.id);
    }
  }

  changeServices() {
    this._accountService.setUserServices(this._accountService.services)
      .subscribe(() => {
        console.log('services are updated');

        this.submitted = false;
      });

    this.submitted = true;
  }
}
