import {AfterViewInit, Directive, ElementRef} from '@angular/core';
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from '@angular/router';

declare const document: any;

@Directive({
  selector: '[routerClass]'
})
export class RouterClassDirective implements AfterViewInit {
  element: ElementRef;
  previousRouteClassName: any;

  constructor(
    el: ElementRef,
    public route: ActivatedRoute,
    public router: Router) {
    this.element = document.getElementsByTagName('html');
  }

  ngAfterViewInit() {
    this.router.events.subscribe((val) => {
      let currentRoute = this.route.root;
      while (currentRoute.children[0] !== undefined) {
        currentRoute = currentRoute.children[0];
      }
      if (val instanceof NavigationStart) {
        this.previousRouteClassName = currentRoute.snapshot.data['className'];
      } else if (val instanceof NavigationEnd) {
        this.element[0]['classList'].remove(this.previousRouteClassName);
        this.element[0]['classList'].add(currentRoute.snapshot.data['className']);
      }
    });
  }
}
