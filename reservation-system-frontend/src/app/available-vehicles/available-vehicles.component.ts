import { Component, Input } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';
import { ReservationService } from '../services/reservation.service';
import { environment } from 'src/environments/environment.development';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-available-vehicles',
  templateUrl: './available-vehicles.component.html',
  styleUrls: ['./available-vehicles.component.css']
})
export class AvailableVehiclesListComponent {

  vehicles: Vehicle[] = [];
  displayedVehicles: Vehicle[] = [];
  @Input() location: string = "";
  @Input() dateRange: string[] = [];
  startDate: string = "";
  endDate: string = "";
  @Input() name: string = "";
  @Input() email: string = "";

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
    this.vehicleService.getVehiclesByLocation(this.location).subscribe(vehicles => {
      this.vehicles = vehicles;
      this.reservationService.getReservedVehicleIdsByDateRange(this.location, this.startDate, this.endDate)
                             .subscribe(reservedIds => {
                               this.displayedVehicles = vehicles.filter(vehicle => {
                                 return !reservedIds.includes(vehicle.vehicleId)
                               });
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
    }
  }

  submitReservation() {

  }

  ngOnInit(): void {
    this.startDate = this.dateRange[0] + " 11:11:11";
    this.endDate = this.dateRange[1] + " 11:11:11";
    this.getAvailableVehicles();
  }

}
