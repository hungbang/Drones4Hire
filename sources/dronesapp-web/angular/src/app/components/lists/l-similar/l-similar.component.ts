import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'l-similar',
  templateUrl: './l-similar.component.html',
  styleUrls: ['./l-similar.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LSimilarComponent implements OnInit {
  @Input() similarProjects: any = [];

  constructor() { }

  ngOnInit() {
  }

}
