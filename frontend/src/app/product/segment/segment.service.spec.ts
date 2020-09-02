import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';

import { SegmentService } from './segment.service';

describe('SegmentService', () => {
  let service: SegmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [SegmentService]
    });
    service = TestBed.inject(SegmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
