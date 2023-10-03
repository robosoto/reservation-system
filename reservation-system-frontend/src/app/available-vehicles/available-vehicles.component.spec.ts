import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { of } from 'rxjs';

import { AvailableVehiclesListComponent } from './available-vehicles.component';
import { VehicleService } from '../services/vehicle.service';
import { ReservationService } from '../services/reservation.service';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('AvailableVehiclesListComponent', () => {
  let component: AvailableVehiclesListComponent;
  let fixture: ComponentFixture<AvailableVehiclesListComponent>;
  let vehicleService: jasmine.SpyObj<VehicleService>;
  let reservationService: jasmine.SpyObj<ReservationService>;

  beforeEach(() => {
    const vehicleServiceSpy = jasmine.createSpyObj('VehicleService', ['getVehiclesByLocation']);
    const reservationServiceSpy = jasmine.createSpyObj('ReservationService', ['getReservedVehicleIdsByDateRange']);

    TestBed.configureTestingModule({
      declarations: [AvailableVehiclesListComponent, TranslatePipe],
      imports: [ReactiveFormsModule], // Import ReactiveFormsModule for FormGroup
      providers: [
        {provide: TranslateService, useClass: TranslateServiceStub},
        PrimeNGConfig,
        {
          provide: VehicleService,
          useValue: vehicleServiceSpy,
        },
        {
          provide: ReservationService,
          useValue: reservationServiceSpy,
        },
      ],
    });

    fixture = TestBed.createComponent(AvailableVehiclesListComponent);
    component = fixture.componentInstance;
    vehicleService = TestBed.inject(VehicleService) as jasmine.SpyObj<VehicleService>;
    reservationService = TestBed.inject(ReservationService) as jasmine.SpyObj<ReservationService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAvailableVehicles when ngOnInit is called', () => {
    const reservationForm: FormGroup = {
      value: {
        location: {
          name: 'Philadelphia',
        },
        dateRange: ['2023-10-01', '2023-10-05'],
      },
    } as any;

    component.reservationForm = reservationForm;
    component.location = 'Philadelphia';
    component.startDate = '2023-10-01';
    component.endDate = '2023-10-05';

    vehicleService.getVehiclesByLocation.and.returnValue(of([]));
    reservationService.getReservedVehicleIdsByDateRange.and.returnValue(of([]));

    spyOn(component, 'getAvailableVehicles').and.callThrough();

    component.ngOnInit();

    expect(component.getAvailableVehicles).toHaveBeenCalled();
  });

});
