import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ReservationService } from '../services/reservation.service';
import { CustomerService } from '../services/customer.service';
import { TranslateService } from '@ngx-translate/core';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import { Router } from '@angular/router';
import { ReservationConfirmForm } from '../types/reservation-confirm-form';
import { CustomerForm } from '../types/customer-form';
import { environment } from 'src/environments/environment.development';

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
  vehicleId: number = 0;
  pricePerDay: number = 0;
  totalReservationPrice: number = 0;
  confirmation: string = '';

  emailToSend:string = '';
  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private reservationService: ReservationService,
    private customerService: CustomerService,
    private messageService:MessageService,
    private _router:Router
   
  ) {}

  ngOnInit() {
    const reservationValues = this.reservationForm!.value;
    this.name = reservationValues.name;
    this.email = reservationValues.email;
    this.location = reservationValues.location.name;
    this.startDate = reservationValues.dateRange[0];
    this.endDate = reservationValues.dateRange[1];
    this.vehicleMake = reservationValues.vehicleMake;
    this.vehicleModel = reservationValues.vehicleModel;
    this.vehicleId = reservationValues.vehicleId;
    this.pricePerDay = reservationValues.pricePerDay;
    this.totalReservationPrice = this.calculateTotalReservationPrice();
  }

  submitReservation() {
    let custForm ={} as CustomerForm;
    custForm.email = this.email;
    custForm.name = this.name;
    this.customerService.signupCustomer(custForm)?.subscribe(data =>{
      this.bookreservation(6);
    
    },
    error => {
       this.messageService.add({ severity: 'error', summary: 'Error', detail: error.error });
       console.log('Error occured');
     })

    
  }

  calculateTotalReservationPrice(): number {
    const start = Math.floor(new Date(this.startDate).getTime() / 1000);
    const end = Math.floor(new Date(this.endDate).getTime() / 1000);
    const totalReservationSeconds = end - start;
    const totalReservationDays = totalReservationSeconds / (60*60*24);
    const price = Math.round(
      this.location === "Cancún" 
                        ? this.pricePerDay * environment.dollarsToPesosMultiplier
                        : this.pricePerDay
    );
    
    // totalPrice = pricePerDay * numDays
    // any amount of time over n days is counted as n + 1 days
    // e.g. 2.1 days is counted as 3 days
    return Math.ceil(totalReservationDays) * price;
  }

  bookreservation(customerid:number){
    let resForm ={} as ReservationConfirmForm;
   
    resForm.customerId = customerid;
    // Get customer from Customer Creation service
    resForm.vehicleId = this.vehicleId;
    resForm.dropoffDate = this.endDate;
    resForm.pickupDate = this.startDate;
    resForm.location = this.location;
    resForm.email = this.email;
    
     this.reservationService.submitReservation(resForm)?.subscribe(
        data => {
         this.emailToSend = this.email;
         this._router.navigate(['confirm-reservation']);
         
        },
        error => {
          console.log(error.error);
         this.messageService.add({ severity: 'error', summary: 'Error', detail: error.error });
          console.log('Error occured');
        }
     )
  }
  
}
