import {Component, HostListener, ViewEncapsulation} from '@angular/core';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {Router} from '@angular/router';
import {AccountService} from '../../../services/account.service/account.service';

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
          { title: 'Drone Pilot', link: '/' },
          { title: 'Client', link: '/' },
        ]
      },
      { title: 'Login', link: '/login' },
      { title: 'Sign Up', link: '/sign-up', classes: '_active' },
    ],
    client: [
      { title: 'Dashboard', link: '/dashboard/client' },
      { title: 'My projects', link: '/my-projects/bidding' },
      {
        title: 'Account',
        isOpened: true,
        children: [
          { title: 'Account settings', link: '/account/client' },
          { title: 'Payment Information' },
          { title: 'Withdrawal request' },
          { title: 'Transactions', link: '/transactions' },
          { title: 'Log out', action: this.logout.bind(this) }
        ]
      },
      { title: 'New project', link: '/project/add', classes: '_active' }
    ],
    pilot: [
      { title: 'Dashboard', link: '/dashboard/pilot' },
      {
        title: 'Account',
        isOpened: true,
        children: [
          { title: 'Account settings', link: '/account/pilot' },
          { title: 'Payment Information' },
          { title: 'Withdrawal request' },
          { title: 'Transactions', link: '/transactions' },
          { title: 'Log out', action: this.logout.bind(this) }
        ]
      },
      { title: 'Find a Job', link: '/search', classes: '_active' }
    ]
  };

  constructor(
    private _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _router: Router
  ) {

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
