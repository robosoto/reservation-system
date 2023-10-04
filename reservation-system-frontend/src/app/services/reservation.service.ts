import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { ReservationConfirmForm } from '../types/reservation-confirm-form';


@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private reservationUrl = environment.apiUrl + "/reservation";

  constructor(private http: HttpClient) { }

  /**
   * Get all vehicles at a given location that have an existing 
   * reservation overlapping with the requested startDate and endDate
   * 
   * @param location location to check for reservations
   * @param startDateTime the start date and time of a newly requested reservation 
   *                      format: YYYY-M-DD HH:mm:ss
   * @param endDateTime the end date and time of a newly requested reservation
   *                    format: YYYY-M-DD HH:mm:ss
   * 
   * @return vehicle IDs for all vehicles that have an existing reservation 
   *         overlapping with the range [startDateTime, endDateTime]
   */
  getReservedVehicleIdsByDateRange(location: string, startDate: string, endDate: string): Observable<number[]> {
    const url = `${this.reservationUrl}/${location}/${startDate}/${endDate}`;
    return this.http.get<number[]>(url);
  }

  submitReservation(reservationForm:ReservationConfirmForm){
    if(reservationForm){
     return this.http.post(`${this.reservationUrl}/confirm`, reservationForm );
    }
    return null;
}

modifyReservation(reservationForm:ReservationConfirmForm){
  if(reservationForm){
   return this.http.put(`${this.reservationUrl}/modify`, reservationForm );
  }
  return null;
}

cancelReservation(reservationForm:ReservationConfirmForm){
  if(reservationForm){
    return this.http.put(`${this.reservationUrl}/cancel`, reservationForm );
   }
   return null;
}

}
