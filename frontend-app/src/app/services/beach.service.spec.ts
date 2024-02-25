import { TestBed } from '@angular/core/testing';

import { BeachService } from './beach.service';

describe('BeachService', () => {
  let service: BeachService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BeachService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
