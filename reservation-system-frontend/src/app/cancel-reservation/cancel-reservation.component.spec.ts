import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { CancelReservationComponent } from './cancel-reservation.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('CancelReservationComponent', () => {
  let component: CancelReservationComponent;
  let fixture: ComponentFixture<CancelReservationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CancelReservationComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    fixture = TestBed.createComponent(CancelReservationComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
