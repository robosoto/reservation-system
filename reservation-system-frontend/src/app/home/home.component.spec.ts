import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { HomeComponent } from './home.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}], // Provide the TranslateService
    });

    fixture = TestBed.createComponent(HomeComponent);
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
