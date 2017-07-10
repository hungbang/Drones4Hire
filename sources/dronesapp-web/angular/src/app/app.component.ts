import {Component, OnInit, ViewContainerRef, ViewEncapsulation} from '@angular/core';
import {ToastsManager} from 'ng2-toastr';
import {
  Router,
  Event as RouterEvent,
  NavigationCancel,
  NavigationEnd,
  NavigationError,
  NavigationStart
} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';

import {ModalService} from './services/modal.service/modal.service';

@Component({
  selector: 'app-root',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent implements OnInit {
  isIndex: boolean = true;

  constructor(
    private modalService: ModalService,
    private toastr: ToastsManager,
    private vRef: ViewContainerRef,
    private router: Router,
    private progressbarService: NgProgressService
  ) {
    this.toastr.setRootViewContainerRef(this.vRef);
    this.router.events.subscribe(
      (event: RouterEvent) => {
        this.navigationInterceptor(event);
      }
    );
  }

  ngOnInit() {
  }

  confirmExit(event) {
    console.log(event);
    this.modalService.pop();
  }

  get modals() {
    return this.modalService.modals;
  }

  // Shows and hides the loading spinner during RouterEvent changes
  private navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
      this.progressbarService.start();
    }
    if (event instanceof NavigationEnd) {
      this.progressbarService.done();
    }

    // Set loading state to false in both of the below events to hide the spinner in case a request fails
    if (event instanceof NavigationCancel) {
      this.progressbarService.done();
    }
    if (event instanceof NavigationError) {
      this.progressbarService.done();
    }
  }
}
