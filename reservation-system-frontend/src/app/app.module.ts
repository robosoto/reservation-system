import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from "@angular/forms";
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { SelectButtonModule } from 'primeng/selectbutton';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ReservationComponent } from './reservation-form/reservation.component';
import { LocationComponent } from './location/location.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { UserInfoFormComponent } from './user-info-form/user-info-form.component';
import { VehiclesListComponent } from './available-vehicles/vehicles.component';
import { LoginComponent } from './login/login.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { NavComponent } from './nav/nav.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ReservationComponent,
    LocationComponent,
    VehicleComponent,
    UserInfoFormComponent,
    VehiclesListComponent,
    LoginComponent,
    UpdateReservationComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    BrowserAnimationsModule,
    CalendarModule,
    HttpClientModule,
    SelectButtonModule,
    InputTextModule,
    TableModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
