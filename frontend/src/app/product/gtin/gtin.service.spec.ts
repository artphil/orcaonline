import { TestBed } from '@angular/core/testing';

import { GtinService } from './gtin.service';

describe('GtinService', () => {
  let service: GtinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GtinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
