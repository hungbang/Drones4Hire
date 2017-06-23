import {ModalService} from './modal.service/modal.service';
import {AppService} from './app.service/app.service';
import {PortfolioService} from './portfolio.service/portfolio.service';
import {BidService} from './bid.service/bid.service';
import {ProjectService} from './project.service/project.service';
import {TransactionService} from './transaction.service/transaction.service';
import {AccountService} from './account.service/account.service';
import {WithdrawService} from './withdraw.service/withdraw.service';
import {PilotsService} from './pilots.service/pilots.service';
import {PaymentService} from './payment.service/payment.service';
import {CommentsService} from './comments.service/comments.service';
import {ClientsService} from './clients.service/clients.service';
import {SimilarService} from './similar.service/similar.service';
import {AuthorizationService} from './authorization.service/authorization.service';
import {RequestService} from './request.service/request.service';
import {TokenService} from './token.service/token.service';
import {CommonService} from './common.service/common.service';
import { PublicService } from './public.service/public.service';
import { ToastrService } from './toastr.service/toastr.service';

export const AppServices = [
  AppService,
  AuthorizationService,
  BidService,
  ClientsService,
  CommonService,
  ModalService,
  PilotsService,
  PortfolioService,
  ProjectService,
  PublicService,
  RequestService,
  SimilarService,
  TokenService,
  TransactionService,
  AccountService,
  WithdrawService,
  PaymentService,
  CommentsService,
  ToastrService,
];
