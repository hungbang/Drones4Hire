import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'b-profile',
  templateUrl: './b-profile.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-profile.component.styl']
})
export class BProfileComponent implements OnInit {
  @Input() showDescription: boolean = true;
  @Input() profile: any;
  status: string = '';
  constructor() { }

  ngOnInit() {
    if (this.profile.groups) {
      this.status = this.profile.groups.reduce((res, el) => {
        if (el.role === 'ROLE_PILOT') {
          res = 'PILOT';
        }
        if (el.role === 'ROLE_CLIENT') {
          res = 'CLIENT';
        }
        return res;
      }, '');
    }
  }

}
