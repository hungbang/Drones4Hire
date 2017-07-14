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
import {UnSubscribeDirective} from './shared/un-subscribe/un-subscribe.directive';
import {takeUntil} from "rxjs/operator/takeUntil";

@Component({
  selector: 'app-root',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.styl']
})
export class AppComponent extends UnSubscribeDirective {

  constructor(
    private modalService: ModalService,
    private toastr: ToastsManager,
    private vRef: ViewContainerRef,
    private router: Router,
    private progressbarService: NgProgressService
  ) {
    super();

    this.toastr.setRootViewContainerRef(this.vRef);
    this.router.events
      .takeUntil(this.ngUnSubscribe)
      .subscribe(
      (event: RouterEvent) => {
        this.navigationInterceptor(event);
      }
    );
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
