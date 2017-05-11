import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AppService } from './services/app.service/app.service';

@Component({
  selector: 'app-root',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {
  isIndex: boolean = true;

  constructor(
    private _appService: AppService
  ) {
  }

  ngOnInit() {
  }
}
