import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { ProductService } from './product.service';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Observable';

describe('ProductService', () => {
  let service: ProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ProductService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: Observable.of({ get: (key) => 'value' })
          }
        }
      ],
    });
    service = TestBed.inject(ProductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
