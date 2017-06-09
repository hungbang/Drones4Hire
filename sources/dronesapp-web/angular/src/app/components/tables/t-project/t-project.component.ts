import {Component, ViewEncapsulation, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 't-project',
  templateUrl: './t-project.component.html',
  styleUrls: ['./t-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TProjectComponent implements OnInit {
  @Input() projects;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.projects = this.route.snapshot.data['projects'];
  }
}
