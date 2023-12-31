import { Component, Input } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';
import { ReservationService } from '../services/reservation.service';
import { environment } from 'src/environments/environment.development';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-available-vehicles',
  templateUrl: './available-vehicles.component.html',
  styleUrls: ['./available-vehicles.component.css']
})
export class AvailableVehiclesListComponent {

  @Input() reservationForm?: FormGroup;
  vehicles: Vehicle[] = [];
  displayedVehicles: Vehicle[] = [];
  isVehicleSelected: boolean = false;
  location: string = "";
  startDate: string = "";
  endDate: string = "";
  dollarsToPesosMultiplier: number = environment.dollarsToPesosMultiplier;

  vehicleTypes: string[] = ["-----"].concat(environment.vehicleTypes);
  selectedVehicleType: string = "-----";
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
                                 return !reservedIds.includes(vehicle.vehicleId);
                               });
                             });
    });
  }

  filterVehiclesByType(): void {
    if (this.selectedVehicleType?.startsWith("-")) {
      this.displayedVehicles = JSON.parse(JSON.stringify(this.vehicles));
    } else {
      this.displayedVehicles = this.vehicles.filter(vehicle => {
        return vehicle.type.toUpperCase() === this.selectedVehicleType.toUpperCase();
      });
    }
  }

  confirmVehicleSelection(make: string, model: string, vehicleId: number, pricePerDay: number) {
    this.reservationForm!.patchValue({
      vehicleMake: make,
      vehicleModel: model,
      vehicleId: vehicleId,
      pricePerDay: pricePerDay
    });
    this.isVehicleSelected = true;
  }

  ngOnInit(): void {
    this.location = this.reservationForm!.value.location.name;
    this.startDate = this.reservationForm!.value.dateRange[0];
    this.endDate = this.reservationForm!.value.dateRange[1];
    this.getAvailableVehicles();
  }

}
