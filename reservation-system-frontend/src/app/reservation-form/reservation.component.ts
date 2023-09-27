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
    // TODO: start location dropdown as "Choose a location", require that a value is selected
    location: [[], Validators.required],
    dateRange: [[], Validators.required]
  });

  name: string = "";
  email: string = "";
  location: string = "";
  dateRange: string[] = [];
  locations = [
    { name: 'Choose Location', code: 'N/A'},
    { name: 'Philadelphia', code: 'PHI' },
    { name: 'Mumbai', code: 'BOM' }
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

  loadAvailableVehicles() {
    let formLocationValue = this.reservationForm.value.location as any;

    this.name = this.reservationForm.value.name!;
    this.email = this.reservationForm.value.email!;
    this.location = formLocationValue.name;
    this.dateRange = this.reservationForm.value.dateRange!;
    this.showVehicles = true;
  }

}
