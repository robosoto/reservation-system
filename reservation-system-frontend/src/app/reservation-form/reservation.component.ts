import { Component, OnInit } from '@angular/core';


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

  ngOnInit() {
    this.location = [
        { name: 'Philadelphia', code: 'PHI' },
        { name: 'Mumbai', code: 'BOM' }
    
    ];
}



}
