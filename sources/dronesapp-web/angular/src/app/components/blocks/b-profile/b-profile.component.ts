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
  constructor() { }

  ngOnInit() {
  }

}
