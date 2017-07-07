import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FaqModel} from "../../services/content.service/faq.interface";

@Component({
  selector: 'faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FaqComponent implements OnInit {
  public faqs: FaqModel[] = [];

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.subscribe(
      () => {
        this.faqs = this.route.snapshot.data['faqs'];
      }
    );
  }

}
