import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {FaqModel} from '../../services/content.service/faq.interface';
import {UnSubscribeDirective} from '../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 'faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FaqComponent extends UnSubscribeDirective implements OnInit {
  public faqs: FaqModel[] = [];

  constructor(
    private route: ActivatedRoute
  ) {
    super();
  }

  ngOnInit() {
    this.route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(
        () => {
          this.faqs = this.route.snapshot.data['faqs'];
        }
      );
  }

}
