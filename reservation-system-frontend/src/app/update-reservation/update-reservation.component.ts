import { Component } from '@angular/core';

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

  location: Location[] | undefined;

  selectedLocation: Location | undefined;

  rangeDates: Date[] | undefined;

  value: string | undefined;

  value2: string | undefined;

  ngOnInit() {
    this.location = [
        { name: 'Location 1', code: 'L1' },
        { name: 'Location 2', code: 'L2' }
    
    ];
}
}
