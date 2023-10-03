import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { ConfirmReservationComponent } from './confirm-reservation.component';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { of } from 'rxjs';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('ConfirmReservationComponent', () => {
  let component: ConfirmReservationComponent;
  let fixture: ComponentFixture<ConfirmReservationComponent>;
  let reservationService: jasmine.SpyObj<ReservationService>;

  beforeEach(() => {
    const reservationServiceSpy = jasmine.createSpyObj('ReservationService', ['submitReservation']);

    TestBed.configureTestingModule({
      declarations: [ConfirmReservationComponent, TranslatePipe],
      imports: [ReactiveFormsModule], // Import ReactiveFormsModule for FormGroup
      providers: [
        {provide: TranslateService, useClass: TranslateServiceStub},
        {
          provide: ReservationService,
          useValue: reservationServiceSpy,
        },
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    fixture = TestBed.createComponent(ConfirmReservationComponent);
    component = fixture.componentInstance;
    reservationService = TestBed.inject(ReservationService) as jasmine.SpyObj<ReservationService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

});
