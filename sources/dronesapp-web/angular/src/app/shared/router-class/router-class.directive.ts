import {AfterViewInit, Directive, ElementRef} from '@angular/core';
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from '@angular/router';

import {UnSubscribeDirective} from '../un-subscribe/un-subscribe.directive';

declare const document: any;

@Directive({
  selector: '[routerClass]'
})
export class RouterClassDirective extends UnSubscribeDirective implements AfterViewInit {
  element: ElementRef;
  previousRouteClassName: any;

  constructor(
    public route: ActivatedRoute,
    public router: Router
  ) {
    super();
    this.element = document.body;
  }

  ngAfterViewInit() {
    this.router.events
      .takeUntil(this.ngUnSubscribe)
      .subscribe((val) => {
        let currentRoute = this.route.root;
        while (currentRoute.children[0] !== undefined) {
          currentRoute = currentRoute.children[0];
        }
        if (currentRoute.snapshot && currentRoute.snapshot.data) {
          if (val instanceof NavigationStart) {
            this.previousRouteClassName = currentRoute.snapshot.data['className'];
          } else if (val instanceof NavigationEnd) {
            this.element['classList'].remove(this.previousRouteClassName);
            currentRoute.snapshot.data['className'] && this.element['classList'].add(currentRoute.snapshot.data['className']);
          }
        }
      });
  }
}
