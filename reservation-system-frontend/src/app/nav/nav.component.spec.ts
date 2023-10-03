import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { NavComponent } from './nav.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('NavComponent', () => {
  let component: NavComponent;
  let fixture: ComponentFixture<NavComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NavComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}]
    });

    fixture = TestBed.createComponent(NavComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
