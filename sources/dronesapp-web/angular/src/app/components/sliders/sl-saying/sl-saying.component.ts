import {Component, ElementRef, OnInit, ViewEncapsulation} from '@angular/core';

declare const $: any;

@Component({
  selector: 'sl-saying',
  templateUrl: './sl-saying.component.html',
  styleUrls: ['./sl-saying.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SlSayingComponent implements OnInit {

  private _element;

  constructor(
    _element: ElementRef
  ) {
    this._element = _element.nativeElement;
  }

  ngOnInit() {

    const saying = $(this._element).find('.sl-saying');

    saying.slick({

      slidesToScroll: 1,
      slidesToShow: 1

    });
  }

}
