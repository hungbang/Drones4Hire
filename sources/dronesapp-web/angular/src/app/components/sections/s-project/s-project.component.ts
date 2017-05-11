import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AppService } from '../../../services/app.service/app.service';
import {ProjectService} from '../../../services/project.service/project.service';
import {BidService} from '../../../services/bid.service/bid.service';
import {CommentsService} from '../../../services/comments.service/comments.service';

@Component({
  selector: 's-project',
  templateUrl: './s-project.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./s-project.component.styl']
})
export class SProjectComponent implements OnInit {
  comments: Array<{}>;
  project: any;
  projectBids: any;
  projectBidsInfo: any;

  constructor(
    public _appService: AppService,
    private _projectService: ProjectService,
    private _bidService: BidService,
    private _commentsService: CommentsService
  ) {
    this.projectBidsInfo = {
      count: 0,
      max: 0,
      left: '2 days 3 hours' // TODO: implement calculating from now to jobDate of the project
    };
  }

  ngOnInit() {
    this.project = this._projectService.project;
    this.projectBids = this._bidService.bids;
    this.projectBids.forEach((bid) => {
      this.projectBidsInfo.max = this.projectBidsInfo.max < bid.amount ? bid.amount : this.projectBidsInfo.max;
    });
    this.projectBidsInfo.count = this.projectBids.length;
    this.comments = this._commentsService.comments;
  }

}
