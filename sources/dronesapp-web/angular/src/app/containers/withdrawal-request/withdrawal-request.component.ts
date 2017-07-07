import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

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
  inPayoneerRegistration: boolean = false;

  constructor(
    private accountService: AccountService,
    private walletService: WalletService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.accountWallet = this.accountService.account.wallet;

    if (!this.accountWallet.withdrawEnabled) {
      this.updateWithdrawalStatus();
    }
  }

  private updateWithdrawalStatus() {
    this.walletService.getWallet()
      .subscribe(
        res => {
          this.accountWallet = res;
          this.checkRegistrationStatus();
        },
        err => {
          console.log('get wallet error:', err);
          this.checkRegistrationStatus();
        }
      );
  }

  private checkRegistrationStatus() {
    if (!this.accountWallet.withdrawEnabled) {
      if (this.route.snapshot.queryParams['payoneer-success']) {
        localStorage.setItem('payoneerPending', 'true');
      }

      if (localStorage.getItem('payoneerPending')) {
        this.inPayoneerRegistration = true;
      }
    } else if (localStorage.getItem('payoneerPending')) {
      localStorage.removeItem('payoneerPending');
    }
  }

}
