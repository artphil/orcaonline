import { TestBed } from '@angular/core/testing';

import { NcmService } from './ncm.service';

describe('NcmService', () => {
  let service: NcmService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NcmService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
