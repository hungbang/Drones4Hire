import {Component, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-preferences',
  templateUrl: './b-preferences.component.html',
  styleUrls: ['./b-preferences.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BPreferencesComponent {
  tabs: Array<Object> = null;

  constructor() {
    this.tabs = [
      {
        link: 'photo',
        text: 'ADD NEW PHOTO',
        visibility: true
      },
      {
        link: 'video',
        text: 'ADD NEW VIDEO',
        visibility: true
      },
    ]
  }
}
