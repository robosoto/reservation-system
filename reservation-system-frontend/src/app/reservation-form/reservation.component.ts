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
        { name: 'Location 1', code: 'L1' },
        { name: 'Location 2', code: 'L2' }
    
    ];
}



}
