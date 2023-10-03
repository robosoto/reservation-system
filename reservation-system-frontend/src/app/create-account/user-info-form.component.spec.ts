import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';
import { of } from 'rxjs/internal/observable/of';
import { UserInfoFormComponent } from './user-info-form.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('UserInfoFormComponent', () => {
  let component: UserInfoFormComponent;
  let fixture: ComponentFixture<UserInfoFormComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserInfoFormComponent, TranslatePipe],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    });

    fixture = TestBed.createComponent(UserInfoFormComponent);
    component = fixture.componentInstance;
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
});
