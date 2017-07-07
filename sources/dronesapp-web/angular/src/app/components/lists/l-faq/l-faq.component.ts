import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'l-faq',
  templateUrl: './l-faq.component.html',
  styleUrls: ['./l-faq.component.styl'],
  encapsulation: ViewEncapsulation.None,
  animations: [
    trigger('itemToggle', [
      transition('void => *', [
        style({  opacity: '0' }),
        animate('0.1s ease', style({ opacity: '1' }))
      ]),
      transition('* => void', [
        animate('0.1s ease', style({ opacity: '0' }))
      ])
    ])
  ]
})
export class LFaqComponent implements OnInit {
  @Input() items: any[] = [];
  activeItem: any = {};

  constructor(
  ) { }

  ngOnInit() {
  }

  toggleItem(item) {
    if (item.active) {
      item.active = false;
      this.activeItem = {};
      return;
    }

    this.activeItem.active = false; // to close previous opened item;
    item.active = true;
    this.activeItem = item;
  }
}
