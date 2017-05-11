import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {SimilarService} from '../../../services/similar.service/similar.service';

@Component({
  selector: 'l-similar',
  templateUrl: './l-similar.component.html',
  styleUrls: ['./l-similar.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LSimilarComponent implements OnInit {

  constructor(
    public _similarService: SimilarService
  ) { }

  ngOnInit() {
  }

}
