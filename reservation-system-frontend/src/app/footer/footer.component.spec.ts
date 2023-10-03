import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { FooterComponent } from './footer.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FooterComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}]
    });

    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
