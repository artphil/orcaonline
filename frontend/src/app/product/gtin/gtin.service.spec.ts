import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';

import { GtinService } from './gtin.service';

describe('GtinService', () => {
  let service: GtinService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      providers: [GtinService]
    });
    service = TestBed.inject(GtinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
