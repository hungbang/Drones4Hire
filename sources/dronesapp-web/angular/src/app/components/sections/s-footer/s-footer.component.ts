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
      { title: 'About Us', link: '' },
      { title: 'Team', link: '' },
      { title: 'Media', link: '' },
      { title: 'Blog', link: '' }
    ],
    clients: [
      { title: 'How it Works', link: '' },
      { title: 'Sign Up', link: '' }
    ],
    pilots: [
      { title: 'How it Works', link: '' },
      { title: 'Sign Up', link: '' }
    ],
    support: [
      { title: 'Faq\'s', link: '' },
      { title: 'Terms & Conditions', link: '' },
      { title: 'Privacy Policy', link: '' },
      { title: 'Contact Us', link: '' }
    ]
  };

  constructor() {}

  get menus() {
    return this._menus;
  }
}
