import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ReviewReservationComponent } from './review-reservation.component';
import { ReservationService } from '../services/reservation.service';
import { CustomerService } from '../services/customer.service';
import { FormGroup } from '@angular/forms';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('ReviewReservationComponent', () => {
  let component: ReviewReservationComponent;
  let fixture: ComponentFixture<ReviewReservationComponent>;
  let reservationService: jasmine.SpyObj<ReservationService>;
  let customerService: jasmine.SpyObj<CustomerService>;

  beforeEach(() => {
    const reservationServiceSpy = jasmine.createSpyObj('ReservationService', ['submitReservation']);
    const customerServiceSpy = jasmine.createSpyObj('CustomerService', ['signupCustomer']);

    TestBed.configureTestingModule({
      declarations: [ReviewReservationComponent, TranslatePipe],
      imports: [RouterTestingModule],
      providers: [
        {provide: TranslateService, useClass: TranslateServiceStub},
        PrimeNGConfig,
        {
          provide: ReservationService,
          useValue: reservationServiceSpy,
        },
        {
          provide: CustomerService,
          useValue: customerServiceSpy,
        },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    fixture = TestBed.createComponent(ReviewReservationComponent);
    component = fixture.componentInstance;
    reservationService = TestBed.inject(ReservationService) as jasmine.SpyObj<ReservationService>;
    customerService = TestBed.inject(CustomerService) as jasmine.SpyObj<CustomerService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize properties from reservationForm', () => {
    const reservationForm: FormGroup = {
      value: {
        name: 'John Doe',
        email: 'johndoe@example.com',
        location: { name: 'Philadelphia' },
        dateRange: ['2023-10-01', '2023-10-05'],
        vehicleMake: 'Toyota',
        vehicleModel: 'Camry',
        vehicleId: 1,
      },
    } as any;

    component.reservationForm = reservationForm;
    component.ngOnInit();

    expect(component.name).toBe('John Doe');
    expect(component.email).toBe('johndoe@example.com');
    expect(component.location).toBe('Philadelphia');
    expect(component.startDate).toBe('2023-10-01');
    expect(component.endDate).toBe('2023-10-05');
    expect(component.vehicleMake).toBe('Toyota');
    expect(component.vehicleModel).toBe('Camry');
    expect(component.vehicleId).toBe(1);
  });
});
