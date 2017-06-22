export interface Card {
  number: number;
  code: number;
  date: string;
  cvv: number;

  validate(): boolean;
  clone(): Card;
}
