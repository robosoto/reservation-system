import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-confirm-reservation',
  templateUrl: './confirm-reservation.component.html',
  styleUrls: ['./confirm-reservation.component.css']
})
export class ConfirmReservationComponent {
  @Input() reservationForm?: FormGroup;
  @Input() emailToSend:string = '';
  name: string = "";
  email: string = "";
  confirmation: string = "";

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private reservationService: ReservationService
  ) {}

  ngOnInit() {
    // const reservationValues = this.reservationForm!.value;
    // this.name = reservationValues.name;
    this.email = this.emailToSend;
    //this.confirmation = reservationValues.confirmation;
    // this.location = reservationValues.location.name;
    // this.startDate = reservationValues.dateRange[0] + " 11:11:11";
    // this.endDate = reservationValues.dateRange[1] + " 11:11:11";
    // this.vehicleMake = reservationValues.vehicleMake;
    // this.vehicleModel = reservationValues.vehicleModel;
  }

  submitReservation() {
    // something like this:
    // this.reservationService.submitReservation(reservationForm);
  }
}
