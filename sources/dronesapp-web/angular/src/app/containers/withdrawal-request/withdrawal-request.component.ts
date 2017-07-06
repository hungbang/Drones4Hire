import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {AccountService} from '../../services/account.service/account.service';
import {WalletService} from '../../services/wallet.service/wallet.service';
import {WalletModel} from '../../services/wallet.service/wallet.interface';

@Component({
  selector: 'withdrawal-request',
  templateUrl: './withdrawal-request.component.html',
  styleUrls: ['./withdrawal-request.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class WithdrawalRequestComponent implements OnInit {
  showSuccessText: boolean = false;
  accountWallet: WalletModel;

  constructor(
    private accountService: AccountService,
    private walletService: WalletService
  ) { }

  ngOnInit() {
    this.accountWallet = this.accountService.account.wallet;

    if (!this.accountWallet.withdrawEnabled) {
      this.walletService.getWallet()
        .subscribe(
          res => {
            this.accountWallet = res;
          },
          err => {
            console.log('get wallet error:', err);
          }
        );
    }
  }

}
