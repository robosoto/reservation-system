import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Vehicle } from '../types/vehicle';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { CustomerForm } from '../types/customer-form';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private customerUrl = environment.apiUrl + "/customer";

  constructor(private http: HttpClient) { }

  signupCustomer(customerForm:CustomerForm){
    if(customerForm){
     return this.http.post(`${this.customerUrl}/signup`, customerForm );
    }
    return null;
}

}
