import { Component, OnInit } from '@angular/core';
import { DialogService } from '../../services/dialog/dialog.service';
import { NzModalComponent } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports:[NzModalComponent],
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  constructor(private dialogService: DialogService) {}

  openStudentDialog() {
    this.dialogService.openStudentDialog(); 
  }

  openClassesDialog() {
    this.dialogService.openClassesDialog(); 
  }

  ngOnInit() { }

}
