import { Component } from '@angular/core';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  stateOptions: any[] = [{label: 'es', value: 'es'}, {label: 'en', value: 'en'}];

  value: string = 'off';
}
