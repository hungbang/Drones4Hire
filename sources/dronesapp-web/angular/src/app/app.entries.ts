import {ModalConfirmationComponent} from './components/modals/modal-confirmation/modal-confirmation.component';
import {ModalInformationComponent} from './components/modals/modal-information/modal-information.component';
import {ModalPaymentComponent} from './components/modals/modal-payment/modal-payment.component';

const modals = [
  ModalConfirmationComponent,
  ModalInformationComponent,
  ModalPaymentComponent,
];

export const entries = [...modals];
