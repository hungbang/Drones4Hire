import {Card} from "./card.interface";

export class CardModel implements Card {
  number: number;
  code: number;
  date: string;
  cvv: number;

  validate(): boolean {
    if (!this._isDateValid()) {
      return false;
    }

    return this._isCvvValid();
  }

  private _isDateValid() {
    return /\d{2}\/\d{2}/.test(this.date);
  }

  private _isCvvValid() {
    return this.cvv >= 100 && this.cvv <= 999;
  }

  public clone(): Card {
    const model = new CardModel();

    model.number = this.number;
    model.code = this.code;
    model.date = this.date;
    model.cvv = this.cvv;

    return model;
  }
}
