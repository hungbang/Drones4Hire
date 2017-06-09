export interface BudgetModel {
  currency: string,
  id: number,
  max: number,
  min: number,
  order: number,
  title: string,
  confirmationValid?: boolean
}
