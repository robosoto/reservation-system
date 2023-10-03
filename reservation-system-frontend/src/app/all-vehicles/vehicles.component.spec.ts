import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';
import { of } from 'rxjs';

import { VehiclesListComponent } from './vehicles.component';
import { VehicleService } from '../services/vehicle.service';
import { Vehicle } from '../types/vehicle';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('VehiclesListComponent', () => {
  let component: VehiclesListComponent;
  let fixture: ComponentFixture<VehiclesListComponent>;
  let vehicleService: jasmine.SpyObj<VehicleService>;

  beforeEach(() => {
    const vehicleServiceSpy = jasmine.createSpyObj('VehicleService', [
      'getAllVehicles',
      'getVehiclesByLocationAndType',
    ]);

    TestBed.configureTestingModule({
      declarations: [VehiclesListComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub},
                   PrimeNGConfig],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    TestBed.overrideProvider(VehicleService, { useValue: vehicleServiceSpy });

    fixture = TestBed.createComponent(VehiclesListComponent);
    component = fixture.componentInstance;
    vehicleService = TestBed.inject(VehicleService) as jasmine.SpyObj<VehicleService>;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

});
