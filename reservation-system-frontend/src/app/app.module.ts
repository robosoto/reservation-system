import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
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
import { UserInfoFormComponent } from './create-account/user-info-form.component';
import { VehiclesListComponent } from './all-vehicles/vehicles.component';
import { LoginComponent } from './login/login.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { NavComponent } from './nav/nav.component';
import { TranslateModule, TranslateLoader, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ReviewReservationComponent } from './review-reservation/review-reservation.component';
import { ConfirmReservationComponent } from './confirm-reservation/confirm-reservation.component';
import { AvailableVehiclesListComponent } from './available-vehicles/available-vehicles.component';
import { CancelReservationComponent } from './cancel-reservation/cancel-reservation.component';
import { ConfirmCancelationComponent } from './confirm-cancelation/confirm-cancelation.component';
import { ToolbarModule } from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';

import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ReservationComponent,
    LocationComponent,
    UserInfoFormComponent,
    VehiclesListComponent,
    LoginComponent,
    UpdateReservationComponent,
    NavComponent,
    ReviewReservationComponent,
    ConfirmReservationComponent,
    AvailableVehiclesListComponent,
    CancelReservationComponent,
    ConfirmCancelationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CalendarModule,
    ConfirmDialogModule,
    HttpClientModule,
    SelectButtonModule,
    InputTextModule,
    TableModule,
    RouterModule,
    ToastModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient],
      },
      defaultLanguage: 'en',
    })
  ],
  exports: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ReservationComponent,
    LocationComponent,
    UserInfoFormComponent,
    VehiclesListComponent,
    LoginComponent,
    UpdateReservationComponent,
    NavComponent,
    ReviewReservationComponent,
    ConfirmReservationComponent,
    AvailableVehiclesListComponent,
    CancelReservationComponent,
    ConfirmCancelationComponent
  ],
  providers: [MessageService,ConfirmationService],
  bootstrap: [AppComponent],
})

export class AppModule { }
