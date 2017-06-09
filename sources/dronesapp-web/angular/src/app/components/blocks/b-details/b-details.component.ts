import {Component, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'b-details',
  templateUrl: './b-details.component.html',
  styleUrls: ['./b-details.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BDetailsComponent {
  constructor(public accountService: AccountService) {
  }
}
