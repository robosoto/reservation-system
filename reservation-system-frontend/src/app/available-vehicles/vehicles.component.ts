import { Component, ViewChild } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';
import { environment } from 'src/environments/environment.development';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesListComponent {

  vehicles: Vehicle[] = [];
  // TODO: regenerate data to match these vehicle types
  vehicleTypes: string[] = ["All Vehicle Types"].concat(environment.vehicleTypes);
  locations: string[] = ["All Locations"].concat(environment.locations);
  selectedVehicleType: string = "All Vehicle Types";
  selectedLocation: string = "All Locations";
  paginatorPageNum: number = 1;

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private vehicleService: VehicleService
    
  ){} 
  

  getAllVehicles() {
    this.vehicleService.getAllVehicles().subscribe(vehicles => this.vehicles = vehicles);
  }

  getVehiclesByLocationAndType(): void {
    this.vehicleService.getVehiclesByLocationAndType(this.selectedLocation,
                                                     this.selectedVehicleType)
                       .subscribe(vehicles => this.vehicles = vehicles);
    this.paginatorPageNum = 1;
  }

  ngOnInit(): void {
    this.getAllVehicles();
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
