import { TestBed, inject } from '@angular/core/testing';

import { BidService } from './bid.service';

describe('ProposalService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BidService]
    });
  });

  it('should ...', inject([BidService], (service: BidService) => {
    expect(service).toBeTruthy();
  }));
});
