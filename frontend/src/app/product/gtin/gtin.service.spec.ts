import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClientModule } from '@angular/common/http';

import { GtinService } from './gtin.service';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

describe('GtinService', () => {
  let service: GtinService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GtinService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: of({ get: (key) => 'value' })
          }
        }
      ]
    });
    service = TestBed.inject(GtinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
