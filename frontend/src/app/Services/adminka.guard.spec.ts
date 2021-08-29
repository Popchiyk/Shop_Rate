import { TestBed } from '@angular/core/testing';

import { AdminkaGuard } from './adminka.guard';

describe('AdminkaGuard', () => {
  let guard: AdminkaGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminkaGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
