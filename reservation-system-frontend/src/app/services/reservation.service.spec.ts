import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReservationService } from './reservation.service';
import { ReservationConfirmForm } from '../types/reservation-confirm-form';
import { environment } from 'src/environments/environment.development';

describe('ReservationService', () => {
  let service: ReservationService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReservationService],
    });

    service = TestBed.inject(ReservationService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify(); // Verify that there are no outstanding HTTP requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should send a GET request to get reserved vehicle IDs by date range', () => {
    const location = 'Philadelphia';
    const startDate = '2023-10-01';
    const endDate = '2023-10-05';

    const mockResponse = [1, 2, 3];

    service.getReservedVehicleIdsByDateRange(location, startDate, endDate).subscribe((response) => {
      expect(response).toEqual(mockResponse);
    });

    const url = `${environment.apiUrl}/reservation/${location}/${startDate}/${endDate}`;
    const req = httpTestingController.expectOne(url);
    expect(req.request.method).toBe('GET');

    req.flush(mockResponse);
  });


});
