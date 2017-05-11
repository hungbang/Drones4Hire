import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 'b-description',
  templateUrl: './b-description.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-description.component.styl']
})
export class BDescriptionComponent implements OnInit {
  @Input() profile;
  constructor() { }

  ngOnInit() {
  }

}
