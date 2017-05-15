import {Directive, ElementRef, OnInit} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import 'rxjs/add/operator/filter';

@Directive({
  selector: '[stateListener]'
})
export class StateListenerDirective implements OnInit {

  private _body;

  constructor(
    private _router: Router
  ) {
    this._body = document.body;
  }

  ngOnInit() {
    let oldClass = null;

    this._router.events
      .filter((route) => route instanceof NavigationEnd)
      .subscribe((route: any) => {
        console.log(this._body.classList);
        if (oldClass) {
          this._body.classList.remove(oldClass);
        }

        oldClass = route.url.slice(1);
        this._body.classList.add(oldClass);
      });
  }
}
