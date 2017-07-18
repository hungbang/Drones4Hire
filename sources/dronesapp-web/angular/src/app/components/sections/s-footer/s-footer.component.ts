import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 's-footer',
  templateUrl: './s-footer.component.html',
  styleUrls: ['./s-footer.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FooterComponent {

  // todo take to service/class
  private _menus = {
    company: [
      { title: 'About Us', link: '/about-us' },
      // { title: 'Team', link: '' },
      // { title: 'Media', link: '' },
      { title: 'Blog', link: '' }
    ],
    clients: [
      { title: 'How it Works', link: '/client/how-it-works' },
      { title: 'Sign Up', link: '/sign-up' }
    ],
    pilots: [
      { title: 'How it Works', link: '/pilot/how-it-works' },
      { title: 'Sign Up', link: '/sign-up' }
    ],
    support: [
      { title: 'Faq\'s', link: '/faq' },
      { title: 'Terms & Conditions', link: '/terms-and-conditions' },
      { title: 'Privacy Policy', link: '/privacy-policy' },
      { title: 'Contact Us', link: '/contact' }
    ]
  };

  constructor() {}

  get menus() {
    return this._menus;
  }
}
