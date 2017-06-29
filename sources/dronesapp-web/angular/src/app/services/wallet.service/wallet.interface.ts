export interface WalletModel {
  balance: number;
  confirmationValid: boolean;
  currency?: string;
  id?: number;
  payoneerRegistrationLink?: string;
  withdrawEnabled: boolean;
}
