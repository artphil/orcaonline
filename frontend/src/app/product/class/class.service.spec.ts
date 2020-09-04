import { TestBed } from '@angular/core/testing';

import { ClassService } from './class.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

describe('ClassService', () => {
  let service: ClassService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClassService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: of({ get: (key) => 'value' })
          }
        }
      ]
    });
    service = TestBed.inject(ClassService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
