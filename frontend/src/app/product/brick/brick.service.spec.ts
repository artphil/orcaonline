import { TestBed } from '@angular/core/testing';

import { BrickService } from './brick.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

describe('BrickService', () => {
  let service: BrickService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [BrickService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: of({ get: (key) => 'value' })
          }
        }
      ]
    });
    service = TestBed.inject(BrickService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
