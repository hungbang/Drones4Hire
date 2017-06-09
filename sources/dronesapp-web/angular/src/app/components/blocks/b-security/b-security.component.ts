import {Component, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'b-security',
  templateUrl: './b-security.component.html',
  styleUrls: ['./b-security.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BSecurityComponent {
  constructor(public accountService: AccountService) {
  }
}
