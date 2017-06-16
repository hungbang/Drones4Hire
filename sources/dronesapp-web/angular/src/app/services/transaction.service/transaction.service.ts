import { Injectable } from '@angular/core';

@Injectable()
export class TransactionService {
  defaultSort = 'date';
  defaultDirection = false;
  itemsPerPage = 100;
  pagesCount = 0;
  currentPage = 0;
  local = {
    transactions: [
      {
        'date': 1461314135713,
        'username': 'Obrien Conner',
        'purpose': 'mollit incididunt occaecat nostrud dolor aliqua velit',
        'type': 'Outgoing',
        'amount': '8433.45'
      },
      {
        'date': 1434374306307,
        'username': 'Laura Sanders',
        'purpose': 'non nostrud laborum in ea laboris quis',
        'type': 'Outgoing',
        'amount': '2519.72'
      },
      {
        'date': 1464805674002,
        'username': 'Hilda Fowler',
        'purpose': 'reprehenderit culpa officia reprehenderit commodo nulla velit',
        'type': 'Incoming',
        'amount': '4037.74'
      },
      {
        'date': 1451040370270,
        'username': 'Jacobs Owens',
        'purpose': 'aliqua proident ad fugiat reprehenderit voluptate magna',
        'type': 'Incoming',
        'amount': '8191.50'
      },
      {
        'date': 1487453297659,
        'username': 'Latonya Pope',
        'purpose': 'non fugiat do officia nostrud exercitation anim',
        'type': 'Outgoing',
        'amount': '8033.46'
      },
      {
        'date': 1438942034725,
        'username': 'Terrell Cochran',
        'purpose': 'non sunt esse laborum consequat cillum non',
        'type': 'Incoming',
        'amount': '3131.46'
      },
      {
        'date': 1429366630959,
        'username': 'Harrison Langley',
        'purpose': 'anim culpa laborum esse esse veniam amet',
        'type': 'Outgoing',
        'amount': '3490.48'
      },
      {
        'date': 1463239500566,
        'username': 'Mendoza Wilkins',
        'purpose': 'est ut labore incididunt in enim exercitation',
        'type': 'Incoming',
        'amount': '7390.43'
      },
      {
        'date': 1444701153443,
        'username': 'Tamika Cline',
        'purpose': 'deserunt laboris Lorem ut nostrud adipisicing enim',
        'type': 'Outgoing',
        'amount': '5281.71'
      },
      {
        'date': 1434303287243,
        'username': 'Joanne Joyce',
        'purpose': 'reprehenderit ex amet aliqua reprehenderit amet elit',
        'type': 'Outgoing',
        'amount': '1612.30'
      },
      {
        'date': 1405893465524,
        'username': 'Susie Bernard',
        'purpose': 'reprehenderit incididunt consequat eu sint ea commodo',
        'type': 'Outgoing',
        'amount': '8334.29'
      },
      {
        'date': 1396255685409,
        'username': 'Hurley Briggs',
        'purpose': 'pariatur sit velit culpa ipsum magna quis',
        'type': 'Outgoing',
        'amount': '1023.93'
      },
      {
        'date': 1468996694096,
        'username': 'Jenkins Mcfarland',
        'purpose': 'nulla labore cupidatat tempor sint Lorem id',
        'type': 'Incoming',
        'amount': '2606.58'
      },
      {
        'date': 1462163955908,
        'username': 'Gabriela Foster',
        'purpose': 'sint magna est ad dolore ullamco deserunt',
        'type': 'Outgoing',
        'amount': '3293.64'
      },
      {
        'date': 1480928060008,
        'username': 'Lucas Wilder',
        'purpose': 'esse esse nisi id tempor pariatur commodo',
        'type': 'Incoming',
        'amount': '3525.44'
      },
      {
        'date': 1425976326988,
        'username': 'Brianna White',
        'purpose': 'exercitation eiusmod est voluptate elit ad excepteur',
        'type': 'Outgoing',
        'amount': '3716.09'
      },
      {
        'date': 1447548921535,
        'username': 'Mueller Banks',
        'purpose': 'incididunt culpa ut aute nisi ut anim',
        'type': 'Outgoing',
        'amount': '2121.27'
      },
      {
        'date': 1439563853862,
        'username': 'Beard Miranda',
        'purpose': 'fugiat aute nisi pariatur reprehenderit velit magna',
        'type': 'Incoming',
        'amount': '3448.47'
      },
      {
        'date': 1408825812255,
        'username': 'Dejesus Keith',
        'purpose': 'tempor elit amet cillum ex sit et',
        'type': 'Outgoing',
        'amount': '1345.89'
      },
      {
        'date': 1423975745767,
        'username': 'Cooper Newman',
        'purpose': 'occaecat voluptate incididunt reprehenderit elit eu ut',
        'type': 'Outgoing',
        'amount': '5046.65'
      },
      {
        'date': 1398163473657,
        'username': 'Jerri Lara',
        'purpose': 'laboris tempor id voluptate cupidatat minim veniam',
        'type': 'Incoming',
        'amount': '6222.20'
      },
      {
        'date': 1452365506706,
        'username': 'Rose Rosario',
        'purpose': 'incididunt incididunt labore do dolor ea in',
        'type': 'Outgoing',
        'amount': '2140.14'
      },
      {
        'date': 1488549390049,
        'username': 'Shepard Glover',
        'purpose': 'ex ullamco qui in minim proident veniam',
        'type': 'Incoming',
        'amount': '7890.75'
      },
      {
        'date': 1430087271038,
        'username': 'Marci Roy',
        'purpose': 'ullamco nostrud ea minim do ut officia',
        'type': 'Incoming',
        'amount': '6861.59'
      },
      {
        'date': 1471828115921,
        'username': 'Cox Spence',
        'purpose': 'deserunt commodo velit laborum ullamco duis exercitation',
        'type': 'Outgoing',
        'amount': '5914.31'
      },
      {
        'date': 1462025724112,
        'username': 'Copeland Fry',
        'purpose': 'ut consequat qui ad voluptate quis ipsum',
        'type': 'Outgoing',
        'amount': '7408.39'
      }
    ]
  };

  constructor() {
    this.updatePagesCount();
  }

  getClientToDroneTransaction(transactions) {
    return transactions.find((transaction) => transaction.type === 'PROJECT_PAYMENT');
  }

  getDroneToPilotTransaction(transactions) {
    return transactions.find((transaction) => transaction.type === 'PAYMENT_RELEASED');
  }

  get getTransactions() {
    this.sortBy(this.defaultSort, this.defaultDirection);
    return this.local.transactions;
  }
  setNewLimit(limit: number) {
    this.itemsPerPage = limit;
    this.updatePagesCount();
    this.currentPage = 0;
  }
  updatePagesCount() {
    this.pagesCount = Math.ceil(this.local.transactions.length / this.itemsPerPage);
  }
  sortBy(prop: string, direction: boolean) {
    this.local.transactions.sort((a: any, b: any) => {
      if (a[prop] < b[prop]) {
        return direction ? -1 : 1;
      } else if (a[prop] > b[prop]) {
        return direction ? 1 : -1;
      } else {
        return 0;
      }
    });
  }
}
