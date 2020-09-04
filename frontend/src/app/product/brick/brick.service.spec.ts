import { TestBed } from '@angular/core/testing';

import { BrickService } from './brick.service';

describe('BrickService', () => {
  let service: BrickService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BrickService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
