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
        { name: 'Philadelphia', code: 'PHI' },
        { name: 'Mumbai', code: 'BOM' }
    
    ];
}
}
