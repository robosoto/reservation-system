
import { CUSTOM_ELEMENTS_SCHEMA, InjectionToken } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MissingTranslationHandler, TranslateCompiler, TranslateLoader, TranslateParser, TranslateService, TranslateStore, USE_DEFAULT_LANG } from '@ngx-translate/core';
import { Observable, of } from 'rxjs';



import { HeaderComponent } from './header.component';

export class TranslateServiceStub {
    public use(key: any): any {
        return of(key);
    }
}

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      providers: [{provide: TranslateService, useClass: TranslateServiceStub}],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]

                     
    });
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
