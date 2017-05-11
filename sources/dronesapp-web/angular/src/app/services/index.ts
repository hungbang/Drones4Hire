import { ModalService } from './modal.service/modal.service';
import { AppService } from './app.service/app.service';
import { PortfolioService } from './portfolio.service/portfolio.service';
import { BidService } from './bid.service/bid.service';
import { ProjectService } from './project.service/project.service';
import { TransactionService } from './transaction.service/transaction.service';
import { AccountService } from './account.service/account.service';
import { WithdrawService } from './withdraw.service/withdraw.service';
import { PilotsService } from './pilots.service/pilots.service';
import { PaymentService } from './payment.service/payment.service';
import { CommentsService } from './comments.service/comments.service';
import { ClientsService } from './clients.service/clients.service';
import {SimilarService} from './similar.service/similar.service';

export const Services = [
  AppService,
  BidService,
  ClientsService,
  ModalService,
  PilotsService,
  PortfolioService,
  ProjectService,
  SimilarService,
  TransactionService,
  AccountService,
  WithdrawService,
  PaymentService,
  CommentsService
];
