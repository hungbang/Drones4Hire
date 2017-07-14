import {Subject} from 'rxjs/Subject';
import {OnDestroy} from '@angular/core';

export abstract class UnSubscribeDirective implements OnDestroy {
  protected readonly ngUnSubscribe = new Subject<void>();

  public ngOnDestroy() {
    this.ngUnSubscribe.next();
    this.ngUnSubscribe.complete();
  }
}
