import { TestBed, inject } from '@angular/core/testing';

import { WithdrawService } from './withdraw.service';

describe('WithdrawService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WithdrawService]
    });
  });

  it('should ...', inject([WithdrawService], (service: WithdrawService) => {
    expect(service).toBeTruthy();
  }));
});
