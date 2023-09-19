import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { VehiclesListComponent } from './available-vehicles/vehicles.component';
import { ReservationComponent } from './reservation-form/reservation.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { LocationComponent } from './location/location.component'


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'vehicles', component: VehiclesListComponent },
  { path: 'reservation-form', component: ReservationComponent},
  { path: 'update-reservation', component: UpdateReservationComponent},
  { path: 'location', component: LocationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
