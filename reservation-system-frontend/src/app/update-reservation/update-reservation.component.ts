import { Component } from '@angular/core';
import { ReservationConfirmForm } from '../types/reservation-confirm-form';
import { FormBuilder, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { Subscription } from 'rxjs';
import { ReservationService } from '../services/reservation.service';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';

interface Location {
  name: string;
  code: string;
}

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrls: ['./update-reservation.component.css']
})
export class UpdateReservationComponent {

  updateReservationForm = this.fb.group({
    reservationId: ["", Validators.required],
    email: ["", [Validators.required, Validators.email]],
    location: [[], Validators.required],
    dateRange: [[], Validators.required]
  
  });

  cancelReservationForm = this.fb.group({
    reservationId: ["", Validators.required],
  });

  localDateRange: string[] = [];

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
    private _router:Router,
    private reservationService:ReservationService,
    private fb: FormBuilder,
    private confirmService: ConfirmationService
  ) {
    this.subscription = this.translate.stream('primeng').subscribe(data => {
      this.primeNGConfig.setTranslation(data);
    });
  }
  ngOnInit() {
    
}

updateReservation() {
  let resForm ={} as ReservationConfirmForm;
   
     resForm.reservationId = this.updateReservationForm.value.reservationId
     this.localDateRange = this.updateReservationForm.value.dateRange!;
     let formLocationValue = this.updateReservationForm.value.location as any;

     resForm.pickupDate = this.localDateRange[0];
     resForm.dropoffDate = this.localDateRange[1];
     resForm.location = formLocationValue.name;
     resForm.email = this.updateReservationForm.value.email;
    console.log(resForm);
     this.reservationService.modifyReservation(resForm)?.subscribe(
        data => {
        
         console.log(data);
         this._router.navigate(['confirm-reservation']);
         
        },
        error => {
         // this.messageService.add({ severity: 'error', summary: 'Error', detail: error.error });
          console.log('Errror occured');
        }
     )

  
  }

  
  /**
   * Check if reservation form is valid and mark all fields as touched
   * in order to show error messages to the user
   * 
   * @returns boolean true if all fields in reservation form are valid
   */ 
  isReservationFormValid(): boolean {
    if (this.updateReservationForm.invalid) {
      this.updateReservationForm.markAllAsTouched();
    }
    return !this.updateReservationForm.invalid;
  }

  isCencelledFormValid():boolean{
    return this.cancelReservationForm.invalid
  }

  cancelReservation() {
    let resForm ={} as ReservationConfirmForm;
    console.log(this.cancelReservationForm.value.reservationId);
    this.confirmService.confirm({
      message: 'Are you sure that you want to Cancel this Reservation?',
      accept: () => {
        resForm.reservationId = this.cancelReservationForm.value.reservationId
       console.log(resForm.reservationId);
       this.reservationService.cancelReservation(resForm)?.subscribe(
        data => {
        
         console.log(data);
         this._router.navigate(['cancel']);
         
        },
        error => {
         // this.messageService.add({ severity: 'error', summary: 'Error', detail: error.error });
          console.log('Errror occured');
        }
     )
      }
    });
  }
}
