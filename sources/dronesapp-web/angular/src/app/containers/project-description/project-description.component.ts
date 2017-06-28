import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NgProgressService} from 'ngx-progressbar';
import * as moment from 'moment';

import {BidModel} from '../../services/bid.service/bid.interface';
import {ProjectModel} from '../../services/project.service/project.interface';
import {AccountService} from '../../services/account.service/account.service';
import {BidService} from '../../services/bid.service/bid.service';
import {CommentsService} from '../../services/comments.service/comments.service';
import {ProjectAttachmentModel} from '../../services/project.service/project-attacment.interface';
import {ProjectService} from '../../services/project.service/project.service';
import {PublicService} from '../../services/public.service/public.service';
import {PaymentService} from '../../services/payment.service/payment.service';
import {ToastrService} from '../../services/toastr.service/toastr.service';
import {ModalService} from '../../services/modal.service/modal.service';
import {ModalConfirmationComponent} from '../../components/modals/modal-confirmation/modal-confirmation.component';
import {ModalPaymentComponent} from "../../components/modals/modal-payment/modal-payment.component";

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
  public pilotAttachments: ProjectAttachmentModel[] = [];
  paymentToken: string = '';
  private _serverBidInfo;

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
    private _publicService: PublicService,
    private projectService: ProjectService,
    private toastrService: ToastrService,
    private _paymentService: PaymentService,
    private modalService: ModalService,
    private progressbarService: NgProgressService
  ) { }

  ngOnInit() {
    const project = this._route.snapshot.parent.data['project'];
    const bids = this._route.snapshot.parent.data['bids'];
    const comments = this._route.snapshot.parent.data['comments'];
    const bidInfo = this._route.snapshot.parent.data['bidInfo'];

    if (!project) {
      return this._router.navigate(['/']);
    }

    this.project = project;
    this.bids = this._bidService.formatBidsToPreview(bids);
    this.comments = this._commentsService.formatCommentToPreview(comments);
    this._serverBidInfo = bidInfo;

    this.fetchClientFullName(this.project.clientId);

    if (this.project.attachments.length) {
      this.fetchAttachments();
    }

    if (this.isPilot) {
      this.pilotBid = this.bids.pop();
    } else {
      this.getPaymentToken();
    }

    this.createBidsInfo(this._serverBidInfo);
    this.getSimilarProjects();
  }

  private getPaymentToken() {
    this._paymentService.getToken()
      .subscribe(
        res => {
          // console.log('payment token', res);
          this.paymentToken = res.clientId;
        },
        err => {
          console.log('payment token error', err);
        }
      );
  }

  fetchClientFullName(id: number) {
    return this._publicService.getPublicAccount(id)
      .subscribe((data) => {
        this.project.client = data || {};
      });
  }

  createBidsInfo(bidInfo, bid?) {
    const hours = moment(this.project.startDate, 'x').diff(moment(), 'hours');
    const days = Math.ceil(hours / 24);
    let maxBid = bidInfo.bidsMax;

    if (bid) {
      maxBid = bid.amount > maxBid ? bid.amount : maxBid;
    }

    this.bidsInfo = {
      max: !maxBid ? 0 : `$${maxBid}`,
      left: hours < 0 ? '-' : `${days} day(s) ${hours % 24} hour(s)`,
      count: bidInfo.bidsCount
    };
  }

  award(bid) {
    this._openConfirm((e) => {
      this.modalService.pop();
      if (e) {
        console.log(bid);
        this.getPayment(bid);
      }
    });
  }

  private _award(bid) {
    this.progressbarService.start();
    this._bidService.award(bid.id, bid.paymentMethod)
      .subscribe(
        () => {
          this.progressbarService.done();
          this.project.bidId = bid.id;
          this.isEdit = false;
        },
        err => {
          this.progressbarService.done();
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try later.');
          } else if (err.status === 400) {
            const body = err.json();
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          } else {
            this.toastrService.showError('Unable to process payment. Please try later.');
          }
        }
      );
  }

  edit() {
    this.isEdit = true;
  }

  private _openConfirm(confirm) {
    this.modalService.push({
      component: ModalConfirmationComponent,
      type: 'ModalConfirmationComponent',
      values: {
        title: '',
        message: 'Do you really want to release payments?',
        confirm_btn_text: 'Yes',
        cancel_btn_text: 'No',
        confirm
      }
    });
  }

  getPayment(bid) {
    this.modalService.push({
      component: ModalPaymentComponent,
      type: 'ModalInformationComponent',
      values: {
        title: 'Choose a payment',
        message: 'Please, choose existed payment or add new before',
        clientToken: this.paymentToken,
        paymentFn: (e) => { this.setPayment(e, bid); }
      }
    });
    return;
  }

  setPayment(nonce, bid) {
    bid.paymentMethod = nonce;
    this.modalService.pop();

    this._award(bid);
  }

  acceptFromPilot(id: number, isProgress: boolean) {
    this._openConfirm((e) =>
      this._acceptPilot(e, id, isProgress));
  }

  private _acceptPilot(isAccepted, id, isProgress) {
    if (isProgress || !isAccepted) {
      this.modalService.pop();
      return;
    }

    this._bidService.accept(id)
      .subscribe((data) => {
        this.modalService.pop();
        this.project.status = 'IN_PROGRESS';
      });
  }

  private _rejectPilot(isAccepted, id, isProgress) {
    if (isProgress || !isAccepted) {
      this.modalService.pop();
      return;
    }

    this._bidService.reject(id)
      .subscribe(() => {
        this.modalService.pop();
        this.project.status = 'NEW';
        delete this.project.bidId;
      });
  }

  rejectFromPilot(id: number, isProgress: boolean) {
    this._openConfirm((e) =>
      this._rejectPilot(e, id, isProgress));
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

    this.progressbarService.start();
    return this._bidService.createBid(data)
      .subscribe(
        (res) => {
          this.progressbarService.done();
          this.pilotBid = this._bidService.formatBidsToPreview([res])[0];
          this.bids.unshift(res);
          this._serverBidInfo.bidsCount += 1;
          this.createBidsInfo(this._serverBidInfo, this.pilotBid);
          this.toastrService.showSuccess('Bid added');
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();

          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          }
        }
      );
  }

  sendComment({ comment, callback }) {
    this.progressbarService.start();
    this._commentsService
      .createComment({ comment, projectId: this.project.id })
      .subscribe((res) => {
          this.progressbarService.done();
          const comment = this._commentsService.formatCommentToPreview([res]);
          this.comments.unshift(...comment);
          callback();
          this.toastrService.showSuccess('Comment added');
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();

          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          }
        }
      );
  }

  editBid(bid) {
    bid.oldBid.comment = bid.comment;
    bid.oldBid.amount = bid.amount;

    this.progressbarService.start();
    return this._bidService.editBid(bid.oldBid)
      .subscribe((res) => {
          this.progressbarService.done();
          this.pilotBid = this._bidService.formatBidsToPreview([res])[0];
          this.bids.unshift(res);
          this.createBidsInfo(this._serverBidInfo, this.pilotBid);
          this.isEdit = false;
          this.toastrService.showSuccess('Changes saved');
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          const body = err.json();

          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          }
        }
      );
  }

  _release(isAccepted, projectId: number) {
    if (!isAccepted) {
      this.modalService.pop();
      return;
    }

    this._paymentService.releasePayment(projectId)
      .subscribe(() => {
        this.modalService.pop();
        this.project.status = 'COMPLETED';
      });
  }

  release(projectId: number) {
    this._openConfirm((e) =>
      this._release(e, projectId));
  }

  fetchAttachments() {
    this.attachments = this.project.attachments.filter(el => el.type === 'PROJECT_ATTACHMENT');
    this.pilotAttachments = this.project.attachments.filter(el => el.type === 'PROJECT_RESULT');
  }

  deleteFile(id: number) {
    this.progressbarService.start();
    this.projectService.deleteAttachment(id)
      .subscribe(
        () => {
          this.progressbarService.done();
          this.attachments = this.attachments.filter(attach => attach.id !== id);
        },
        err => {
          this.progressbarService.done();
          console.log('delete attached file error', err);
        }
      );
  }

  getSimilarProjects() {
    const search = {
      page: 1,
      pageSize: 3,
      serviceCategoryId: this.project.service.category.id,
      statuses: ['NEW']
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
