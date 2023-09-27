import { Component } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';
import { environment } from 'src/environments/environment.development';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesListComponent {

  vehicles: Vehicle[] = [];
  vehicleTypes: string[] = ["All Vehicle Types"].concat(environment.vehicleTypes);
  locations: string[] = ["All Locations"].concat(environment.locations);
  selectedVehicleType: string = "All Vehicle Types";
  selectedLocation: string = "All Locations";
  paginatorPageNum: number = 0;

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

}
