import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {  RouterLink, RouterOutlet } from '@angular/router';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzModalComponent } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,NzIconModule, NzLayoutModule, NzMenuModule,RouterOutlet,RouterLink, NzModalComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  isCollapsed = false;
}
