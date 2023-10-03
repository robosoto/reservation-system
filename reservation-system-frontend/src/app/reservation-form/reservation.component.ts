import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { Subscription } from 'rxjs';

interface Location {
  name: string;
  code: string;
}

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent  {

  reservationForm = this.fb.group({
    name: ["", Validators.required],
    email: ["", [Validators.required, Validators.email]],
    location: [[], Validators.required],
    dateRange: [[], Validators.required],
    vehicleMake: "",
    vehicleModel: "",
    vehicleId: 0
  });

  name: string = "";
  email: string = "";
  location: string = "";
  dateRange: string[] = [];
  locations = [
    { name: 'Choose Location', code: 'N/A'},
    { name: 'Philadelphia', code: 'PHI' },
    { name: 'Cancún', code: 'CAN' }
  ];
  showVehicles: boolean = false;
  subscription: Subscription;

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private fb: FormBuilder
  ) {
    this.subscription = this.translate.stream('primeng').subscribe(data => {
      this.primeNGConfig.setTranslation(data);
    });
  }

  /**
   * Check if reservation form is valid and mark all fields as touched
   * in order to show error messages to the user
   * 
   * @returns boolean true if all fields in reservation form are valid
   */ 
  isReservationFormValid(): boolean {
    if (this.reservationForm.invalid) {
      this.reservationForm.markAllAsTouched();
    }
    return !this.reservationForm.invalid;
  }

  /**
   * Validate reservation form and load available vehicles if it is valid
   */
  validateFormAndLoadAvailableVehicles() {
    if (this.isReservationFormValid()) {}
      this.showVehicles = true;
    }

  }

