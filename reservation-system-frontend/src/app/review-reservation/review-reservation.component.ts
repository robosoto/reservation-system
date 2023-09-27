import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-review-reservation',
  templateUrl: './review-reservation.component.html',
  styleUrls: ['./review-reservation.component.css']
})
export class ReviewReservationComponent {
  @Input() reservationForm?: FormGroup;
  name: string = "";
  email: string = "";
  location: string = "";
  startDate: string = "";
  endDate: string = "";
  vehicleMake: string = "";
  vehicleModel: string = "";

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private reservationService: ReservationService
  ) {}

  ngOnInit() {
    const reservationValues = this.reservationForm!.value;
    this.name = reservationValues.name;
    this.email = reservationValues.email;
    this.location = reservationValues.location.name;
    this.startDate = reservationValues.dateRange[0] + " 11:11:11";
    this.endDate = reservationValues.dateRange[1] + " 11:11:11";
    this.vehicleMake = reservationValues.vehicleMake;
    this.vehicleModel = reservationValues.vehicleModel;
  }

  submitReservation() {
    // something like this:
    // this.reservationService.submitReservation(reservationForm);
  }
  
}
