import { Component } from '@angular/core';
import { Vehicle } from '../types/vehicle';
import { VehicleService } from '../services/vehicle.service';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesListComponent {

  vehicles: Vehicle[] = [];

  constructor(
    private vehicleService: VehicleService
  ) {}

  getAllVehicles(): void {
    this.vehicleService.getAllVehicles().subscribe(vehicles => this.vehicles = vehicles);
  }

  ngOnInit(): void {
    this.getAllVehicles();
  }

}
