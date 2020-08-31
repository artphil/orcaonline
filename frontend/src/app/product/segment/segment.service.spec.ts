import { TestBed } from '@angular/core/testing';

import { SegmentService } from './segment.service';

describe('SegmentService', () => {
  let service: SegmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SegmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
