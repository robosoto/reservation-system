import { Component, ViewChild } from '@angular/core';
import { Paginator } from 'primeng/paginator';

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
  vehicleTypes: string[] = environment.vehicleTypes;
  locations: string[] = ["Choose Location"].concat(environment.locations);
  selectedLocation?: string;
  paginatorPageNum: number = 1;

  constructor(
    public translate: TranslateService,
    public primeNGConfig: PrimeNGConfig,
    private vehicleService: VehicleService
    
  ){} 
  

  getAllVehicles(): void {
    this.vehicleService.getAllVehicles().subscribe(vehicles => this.vehicles = vehicles);
  }

  getVehiclesByLocation(event: DropdownChangeEvent): void {
    this.vehicleService.getVehiclesByLocation(this.selectedLocation!).subscribe(vehicles => this.vehicles = vehicles);
    this.paginatorPageNum = 1;
  }

  ngOnInit(): void {
    this.getAllVehicles();
  }

}
