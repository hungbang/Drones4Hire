import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-project-description',
  templateUrl: './b-project-description.component.html',
  styleUrls: ['./b-project-description.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectDescriptionComponent implements OnInit {
  @Input() description: string = '';

  constructor() { }

  ngOnInit() {
  }

}
