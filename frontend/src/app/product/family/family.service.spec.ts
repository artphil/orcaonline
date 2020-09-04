import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FamilyService } from './family.service';

describe('FamilyService', () => {
  let service: FamilyService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [FamilyService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: of({ get: (key) => 'value' })
          }
        }
      ]
    });
    service = TestBed.inject(FamilyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
