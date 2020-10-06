import { TestBed } from '@angular/core/testing';

import { UserTypeService } from './user-type.service';

describe('UserTypeService', () => {
  let service: UserTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
