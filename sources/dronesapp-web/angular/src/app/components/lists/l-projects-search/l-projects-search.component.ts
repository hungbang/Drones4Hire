import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';
import { ProjectService } from '../../../services/project.service/project.service';

@Component({
  selector: 'l-projects-search',
  templateUrl: './l-projects-search.component.html',
  styleUrls: ['./l-projects-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LProjectsSearchComponent implements OnInit {

  @Input() projects;

  constructor() {
  }

  ngOnInit() {
  }

}
