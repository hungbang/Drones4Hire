import {Component, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 't-project',
  templateUrl: './t-project.component.html',
  styleUrls: ['./t-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TProjectComponent {
  @Input() projects;

  constructor() {
  }
}
