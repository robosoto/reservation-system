import { Component } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';
import { ReservationService } from '../services/reservation.service';
import { environment } from 'src/environments/environment.development';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-vehicles',
  templateUrl: './available-vehicles.component.html',
  styleUrls: ['./available-vehicles.component.css']
})
export class AvailableVehiclesListComponent {

  vehicles: Vehicle[] = [];
  displayedVehicles: Vehicle[] = [];
  location: string = "Mumbai"; // TODO pass in from reservations page
  startDate: string = "2023-10-20 11:11:11";
  endDate: string = "2023-10-27 11:11:11";
  vehicleTypes: string[] = ["All Vehicle Types"].concat(environment.vehicleTypes);
  selectedVehicleType: string = "All Vehicle Types";
  paginatorPageNum: number = 0;

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private vehicleService: VehicleService,
    private reservationService: ReservationService
  ){} 

  getAvailableVehicles() {
    this.vehicleService.getAllVehicles().subscribe(vehicles => {
      this.vehicles = vehicles;
      this.reservationService.getReservedVehicleIdsByDateRange(this.location, this.startDate, this.endDate)
                             .subscribe(reservedIds => {
                                console.log(reservedIds);
                               this.displayedVehicles = vehicles.filter(vehicle => !reservedIds.includes(vehicle.vehicleId));
                               console.log(JSON.stringify(this.displayedVehicles));
                             });
    });
  }

  filterVehiclesByType(): void {
    if (this.selectedVehicleType?.startsWith("All")) {
      this.displayedVehicles = JSON.parse(JSON.stringify(this.vehicles));
    } else {
      this.displayedVehicles = this.vehicles.filter(vehicle => {
        return vehicle.type.toUpperCase() === this.selectedVehicleType.toUpperCase();
      });
      console.log(this.displayedVehicles)
    }
  }

  ngOnInit(): void {
    this.getAvailableVehicles();
  }

  /**
   * Filter full list of vehicles by location and vehicle type
   * which the user selects via dropdown.
   */
  // filterVehiclesByLocationAndType() {
  //   let filteredVehicles = this.allVehicles;
  //   console.log(this.selectedLocation, this.selectedVehicleType)

  //   if (!this.selectedLocation?.startsWith("All")) {
  //     console.log("location filter")
  //     // apply location filter
  //     filteredVehicles = this.allVehicles.filter(vehicle => {
  //       vehicle.location.toUpperCase() === this.selectedLocation?.toUpperCase();
  //     });
  //     console.log(filteredVehicles)
  //   }

  //   if (!this.selectedVehicleType?.startsWith("All")) {
  //     console.log("type filter")
  //     // apply vehicle type filter
  //     filteredVehicles = filteredVehicles.filter(vehicle => {
  //       vehicle.type.toUpperCase() === this.selectedVehicleType?.toUpperCase();
  //     });
  //   }

  //   this.displayedVehicles = filteredVehicles;
  //   this.paginatorPageNum = 1;
  // }

}
