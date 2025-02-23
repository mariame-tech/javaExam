import { Injectable } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { ClassesComponent } from '../../pages/classes/classes.component';
import { StudentComponent } from '../../pages/students/student.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(private modal: NzModalService) { }
  openStudentDialog() {
    this.modal.create({
      nzTitle: 'Student Details',
      nzContent: StudentComponent, // Utilisez StudentComponent comme contenu du dialog
      nzFooter: null, // Pas de footer
      nzWidth: '60%' // Largeur du dialog
    });
  }

  openClassesDialog() {
    this.modal.create({
      nzTitle: 'Classes Details',
      nzContent: ClassesComponent, // Utilisez ClassesComponent comme contenu du dialog
      nzFooter: null, // Pas de footer
      nzWidth: '60%' // Largeur du dialog
    });
  }
}
