import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 's-preview',
  templateUrl: './s-preview.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./s-preview.component.styl']
})
export class SPreviewComponent implements OnInit {
  @Input() profile;
  @Input() showDescription = true;
  constructor() { }

  ngOnInit() {}

}
