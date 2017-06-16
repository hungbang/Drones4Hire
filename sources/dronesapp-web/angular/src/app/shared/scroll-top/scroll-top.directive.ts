import {Directive, OnInit} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";

@Directive({
  selector: '[scrollTop]'
})
export class ScrollTopDirective implements OnInit {

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.router.events
      .filter((route) => route instanceof NavigationEnd)
      .subscribe(() => {
        window.scrollTo(0, 0);
      });

  }

}
