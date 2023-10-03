import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { ConfirmCancelationComponent } from './confirm-cancelation.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('ConfirmCancelationComponent', () => {
  let component: ConfirmCancelationComponent;
  let fixture: ComponentFixture<ConfirmCancelationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConfirmCancelationComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    fixture = TestBed.createComponent(ConfirmCancelationComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
