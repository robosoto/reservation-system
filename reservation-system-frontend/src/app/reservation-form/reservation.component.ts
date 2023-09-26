import { Component, OnInit } from '@angular/core';
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

  location: Location[] | undefined;

  selectedLocation: Location | undefined;

  rangeDates: Date[] | undefined;

  value: string | undefined;
  subscription: Subscription;
  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig
    
  ) {
    this.subscription = this.translate.stream('primeng').subscribe(data => {
      this.primeNGConfig.setTranslation(data);
    });
  }
  ngOnInit() {
    this.location = [
        { name: 'Philadelphia', code: 'PHI' },
        { name: 'Mumbai', code: 'BOM' }
    
    ];
}



}
