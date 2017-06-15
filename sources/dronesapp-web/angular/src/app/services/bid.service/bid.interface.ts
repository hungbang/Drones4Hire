export interface BidModel {
  amount: number;
  comment: string;
  confirmationValid?: boolean;
  currency: string;
  id: number;
  projectId: number;
  pilotId: number;
  createdAt: number;
  account?: any
}
