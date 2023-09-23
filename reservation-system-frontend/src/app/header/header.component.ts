import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  stateOptions: any[] = [{label: 'es', value: 'es'}, {label: 'en', value: 'en'}];

  value: string = 'off';

  constructor(public translate: TranslateService) {
    this.changeLanguage('en');
  }

  public changeLanguage(language: string): void {
    this.translate.use(language);
  }

}
