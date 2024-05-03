import { TestBed } from '@angular/core/testing';

import { EventProcessingService } from './event-processing.service';

describe('EventProcessingService', () => {
  let service: EventProcessingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventProcessingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
