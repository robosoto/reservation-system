import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { LocationComponent } from './location.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}


describe('LocationComponent', () => {
  let component: LocationComponent;
  let fixture: ComponentFixture<LocationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LocationComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}], // Provide the TranslateService
    });

    fixture = TestBed.createComponent(LocationComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should inject TranslateService', () => {
    const translateService = TestBed.inject(TranslateService);
    expect(translateService).toBeTruthy();
  });
});
