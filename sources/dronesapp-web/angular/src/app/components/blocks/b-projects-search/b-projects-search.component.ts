import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'b-projects-search',
  templateUrl: './b-projects-search.component.html',
  styleUrls: ['./b-projects-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectsSearchComponent implements OnInit {

  public projects;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.projects = this.route.snapshot.data['projects'];
  }
}
