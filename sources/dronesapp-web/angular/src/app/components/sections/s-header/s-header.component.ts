import {Component, HostListener, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';

import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {AccountService} from '../../../services/account.service/account.service';
import {ModalService} from '../../../services/modal.service/modal.service';
import {ModalInformationComponent} from '../../modals/modal-information/modal-information.component';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 's-header',
  templateUrl: './s-header.component.html',
  styleUrls: ['./s-header.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent {
  public isOpenMenu = false;
  public canCloseMenu = false;

  private _menus = {
    guest: [
      { title: 'Blog', link: '/' },
      {
        title: 'How it works',
        isOpened: true,
        children: [
          { title: 'Drone Pilot', link: '/pilot/how-it-works' },
          { title: 'Client', link: '/client/how-it-works' },
        ]
      },
      { title: 'Login', link: '/login' },
      { title: 'Sign Up', link: '/sign-up', classes: '_active' },
    ],
    client: [
      { title: 'Dashboard', link: '/dashboard/client/1' },
      { title: 'My projects', link: '/my-projects/bidding/1' },
      {
        title: 'Account',
        isOpened: true,
        children: [
          { title: 'Account settings', link: '/account/client' },
          // { title: 'Payment Information', link: '/payment' }, // TODO: remove if no changes about it
          { title: 'Withdrawal request', link: '/withdrawal-request' },
          { title: 'Transactions', link: '/transactions' },
          { title: 'Log out', action: this.logout.bind(this) }
        ]
      },
      { title: 'New project', action: this.goToPostAProjectIfAccessExist.bind(this), link: '/project/manage/add', classes: '_active' }
    ],
    pilot: [
      { title: 'Dashboard', link: '/dashboard/pilot/1' },
      {
        title: 'Account',
        isOpened: true,
        children: [
          { title: 'Account settings', link: '/account/pilot' },
          { title: 'Withdrawal request', link: '/withdrawal-request' },
          { title: 'Transactions', link: '/transactions' },
          { title: 'Log out', action: this.logout.bind(this) }
        ]
      },
      { title: 'Find a Job', action: this.goToFindAJobIfAccessExist.bind(this), classes: '_active' }
    ]
  };

  constructor(
    private _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router,
    private _modalService: ModalService,
    private toastrService: ToastrService,
  ) {

  }

  goToFindAJobIfAccessExist() {
    if (this._accountService.license && this._accountService.license.verified) {
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
                title: 'Find a job',
                message: 'Your license and/or certificate were not verified yet. Please upload it on Account settings, to gain access to job board.'
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

  goToPostAProjectIfAccessExist() {
    if (!this._modalService) {
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

    this._router.navigate(['/project', 'manage', 'add']);
  }

  logout() {
    this._authorizationService.logout();
    this._router.navigate(['/']);
  }

  get menu() {
    if (!this._authorizationService.isUserLogin) {
      return this._menus.guest;
    } else if (this._accountService.isUserPilot()) {
      return this._menus.pilot;
    }

    return this._menus.client;
  }

  closeMenu() {
    this.isOpenMenu = false;
  }

  openMenu() {
    this.isOpenMenu = true;
    this.canCloseMenu = false;
  }

  @HostListener('window:resize', ['$event'])
  closeMenuOnResize(event) {
    this.closeMenu();
  }
  @HostListener('document:click', ['$event'])
  closeMenuOnClick(event) {
    if (this.canCloseMenu) {
      this.closeMenu();
    }
    this.canCloseMenu = true;
  }
}
