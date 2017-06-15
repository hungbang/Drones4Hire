import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

import {BidModel} from '../../services/bid.service/bid.interface';
import {ProjectModel} from '../../services/project.service/project.interface';
import {AccountService} from '../../services/account.service/account.service';
import {BidService} from '../../services/bid.service/bid.service';
import {CommentsService} from '../../services/comments.service/comments.service';
import {ProjectAttachmentModel} from '../../services/project.service/project-attacment.interface';
import {ProjectService} from "../../services/project.service/project.service";

@Component({
  selector: 'project-description',
  templateUrl: './project-description.component.html',
  styleUrls: ['./project-description.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ProjectDescriptionComponent implements OnInit {
  public bidsInfo;
  public bids: BidModel[];
  public project: ProjectModel;
  public comments: Array<{}>;
  public pilotBid: BidModel|{} = {};
  public isEdit = false;
  public attachments: ProjectAttachmentModel[] = [];
  public similarProjects: ProjectModel[] = [];

  get isClient() {
    return this._accountService.isUserClient();
  }

  get isPilot() {
    return this._accountService.isUserPilot();
  }

  get activeBidId() {
    return this.project.bidId;
  }

  constructor(
    private _route: ActivatedRoute,
    private _accountService: AccountService,
    private _bidService: BidService,
    private _commentsService: CommentsService,
    private _router: Router,
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    const project = this._route.snapshot.parent.data['project'];

    if (!project) {
      return this._router.navigate(['/']);
    }

    this.project = project;
    this.bids = this._route.snapshot.parent.data['bids'];
    this.comments = this._route.snapshot.parent.data['comments'];
    if (this.project.attachments.length) {
      this.fetchAttachments();
    }

    if (this.isPilot) {
      this.pilotBid = this.bids.pop();
    }

    this.createBidsInfo(this.bids);
    this.getSimilarProjects();
  }

  createBidsInfo(bids) {
    const bid = bids.reduce((maxBid, bid) =>
      maxBid.amount < bid.amount ? bid : maxBid, {amount: 0});

    this.bidsInfo = {
      max: bid.amount,
      left: '2 days 3 hours',
      count: this.bids.length
    };
  }

  award(bid) {
    this._bidService.award(bid.id)
      .subscribe(() => {
        this.project.bidId = bid.id;
        this.isEdit = false;
      });
  }

  edit() {
    this.isEdit = true;
  }

  acceptFromPilot(id: number, isProgress: boolean) {
    if (isProgress) { return; }

    this._bidService.accept(id)
      .subscribe(() => {});
  }

  rejectFromPilot(id: number, isProgress: boolean) {
    if (isProgress) { return; }

    this._bidService.reject(id)
      .subscribe(() => {
        this.project.status = 'NEW';
        delete this.project.bidId;
      });
  }

  submitBid(bid: BidModel) {
    const data = Object.assign({
      amount: 0,
      comment: '',
      confirmationValid: true,
      currency: 'USD',
      id: 0,
      projectId: this.project.id
    }, bid);

    return this._bidService.createBid(data)
      .subscribe((res) => {
        this.pilotBid = res;
        this.bids.unshift(res);
        this.createBidsInfo(this.bids);
      });
  }

  send({ comment, callback }) {
    this._commentsService
      .createComment({ comment, projectId: this.project.id })
      .subscribe((res) => {
        this.comments.push(res);
        callback();
      });
  }

  editBid(bid: BidModel) {
    return this._bidService.editBid(bid)
      .subscribe((res) => {
        this.bids = this.bids.filter((bid) => bid.id !== res.id);
        this.pilotBid = res;
        this.bids.unshift(res);
        this.createBidsInfo(this.bids);
        this.isEdit = false;
      });
  }

  fetchAttachments() {
    this.attachments = this.project.attachments.filter(el => el.type === 'PROJECT_ATTACHMENT');
  }

  deleteFile(id: number) {
    this.projectService.deleteAttachment(id)
      .subscribe(
        () => {
          this.attachments = this.attachments.filter(attach => attach.id !== id);
        },
        err => {
          console.log('delete attached file error', err);
        }
      );
  }

  getSimilarProjects() {
    const search = {
      page: 1,
      pageSize: 3,
      serviceCategoryId: this.project.service.category.id,
      status: 'NEW'
    };

    this.projectService.getProjects(search).subscribe(
      res => {
        if (res.totalResults) {
          this.similarProjects = res.results.map(data => data.project);
        }
      }
    )
  }
}
