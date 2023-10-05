import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from '../types/vehicle';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private vehicleUrl = environment.apiUrl + "/vehicle";

  constructor(private http: HttpClient) { }

  getAllVehicles(): Observable<Vehicle[]> {
    const url = `${this.vehicleUrl}/all`;
    return this.http.get<Vehicle[]>(url);
  }

  getVehiclesByLocation(location: string): Observable<Vehicle[]> {
    const url = `${this.vehicleUrl}/location/${location}`;
    return this.http.get<Vehicle[]>(url);
  }

  getVehiclesByLocationAndType(location: string, type: string): Observable<Vehicle[]> {
    let url = `${this.vehicleUrl}/filter?`;

    if (!location.startsWith("-")) {
      url += `location=${location}&`;
    }
    if (!type.startsWith("-")) {
      url += `vehicleType=${type}`
    }
    return this.http.get<Vehicle[]>(url);
  }

  getVehiclesByType(type: string): Observable<Vehicle[]> {
    const url = `${this.vehicleUrl}/type/${type}`;
    return this.http.get<Vehicle[]>(url);
  }

}
