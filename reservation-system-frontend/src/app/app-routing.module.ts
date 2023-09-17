import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { VehicleComponent } from './vehicle/vehicle.component';
import { VehiclesComponent } from './available-vehicles/vehicles.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'vehicle/all', component: VehiclesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
