import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { VehiclesListComponent } from './available-vehicles/vehicles.component';
import { ReservationComponent } from './reservation-form/reservation.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { LocationComponent } from './location/location.component'
import { LoginComponent } from './login/login.component';
import { UserInfoFormComponent } from './create-account/user-info-form.component';
import { ReviewReservationComponent } from './review-reservation/review-reservation.component';
import { ConfirmReservationComponent } from './confirm-reservation/confirm-reservation.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'vehicles', component: VehiclesListComponent },
  { path: 'reservation-form', component: ReservationComponent},
  { path: 'update-reservation', component: UpdateReservationComponent},
  { path: 'location', component: LocationComponent},
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: UserInfoFormComponent},
  { path: 'review', component: ReviewReservationComponent},
  { path: 'confirm', component: ConfirmReservationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
