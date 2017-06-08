import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ProjectModel} from '../../services/project.service/project.interface';
import {ActivatedRoute} from '@angular/router';
import {BidModel} from '../../services/bid.service/bid.interface';
import {BidService} from '../../services/bid.service/bid.service';
import {AccountService} from '../../services/account.service/account.service';
import {CommentsService} from '../../services/comments.service/comments.service';

@Component({
  selector: 'project-page',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class ProjectComponent implements OnInit {
  public bidsInfo;
  public bids: BidModel[];
  public project: ProjectModel;
  public activeId: number;
  public comments: Array<{}>;
  public pilotBid: BidModel|{} = {};
  public isEdit = false;

  constructor(private _accountService: AccountService,
              private _bidService: BidService,
              private _commentsService: CommentsService,
              private route: ActivatedRoute) {
  }

  get isClient() {
    return this._accountService.isUserClient();
  }

  get isPilot() {
    return this._accountService.isUserPilot();
  }

  // todo get user Id
  get userId() {
    return -1;
  }

  ngOnInit() {
    this.project = this.route.snapshot.data['project'];
    this.bids = this.route.snapshot.data['bids'];
    this.comments = this.route.snapshot.data['comments'];
    this.activeId = this.project.pilotId;

    if (this.isPilot) {
      this.pilotBid = this.bids.find((bid) => bid.pilotId === this.userId);
    }

    this.createBidsInfo(this.bids);
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

  award(id) {
    this._bidService.award(id)
      .subscribe(() => {
        this.activeId = id;
        this.isEdit = false;
      });
  }

  edit() {
    this.isEdit = true;
  }

  acceptFromPilot(id: number) {
    this._bidService.accept(id);
  }

  rejectFromPilot(id: number) {
    this._bidService.reject(id);
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
}
