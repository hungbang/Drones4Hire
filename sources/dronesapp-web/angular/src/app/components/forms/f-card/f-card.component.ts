import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {Card} from "../../../services/payment.service/card.interface";
import {CardModel} from "../../../services/payment.service/card-model";

@Component({
  selector: 'f-card',
  templateUrl: './f-card.component.html',
  styleUrls: ['./f-card.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FCardComponent implements OnInit {
  @Input() model: Card;
  @Input() title: string;

  @Output() send = new EventEmitter<Card>();

  public card;

  constructor() { }

  ngOnInit() {
    if (!this.model) {
      this.card = new CardModel();
    } else {
      this.card = this.model.clone();
    }
  }

  onSubmit(form) {
    if (!this.card.validate() || !form.valid) {
      return;
    }

    this.send.emit(this.card);
  }
}
