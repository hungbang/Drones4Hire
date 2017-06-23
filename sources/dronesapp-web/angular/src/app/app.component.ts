import {Component, OnInit, ViewContainerRef, ViewEncapsulation} from '@angular/core';
import {ToastsManager} from 'ng2-toastr';

@Component({
  selector: 'app-root',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {
  isIndex: boolean = true;

  constructor(private toastr: ToastsManager, private vRef: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(this.vRef);
  }

  ngOnInit() {
  }
}
