import { Component } from '@angular/core';
import { NzModalComponent, NzModalService } from 'ng-zorro-antd/modal';
import { StudentService } from '../../services/student/student.service';
import { StudentComponent } from '../students/student.component';
import { StudentDtoResponse } from '../../models/student.dto';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzTableModule } from 'ng-zorro-antd/table';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-list-students',
  standalone: true,
  imports: [NzModalComponent,NzTableModule,CommonModule,NzIconModule],
  templateUrl: './list-students.component.html',
  styleUrl: './list-students.component.scss'
})
export class ListStudentsComponent {
  students: StudentDtoResponse[] = []; // Liste des étudiants

  constructor(
    private studentService: StudentService,
    private modal: NzModalService,
    private notification: NzNotificationService
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {
    this.studentService.getAllStudents().subscribe(
      (data) => {
        this.students = data; 
      },
      (error) => {
        this.notification.error(
          'Error', // Titre de la notification
          'Failed to load students!' // Message de la notification
        );
      }
    );

  }

  onDelete(student: StudentDtoResponse): void {

    this.modal.confirm({
      nzTitle: 'Confirmation',
      nzContent: 'Are you sure you want to delete this student?',
      nzOkText: 'Yes',
      nzCancelText: 'No',
      nzOkType: 'primary',
      nzOkDanger: true, // Bouton "Yes" en rouge
      nzOnOk: () => {
        // Si l'utilisateur clique sur "Yes", supprimer l'étudiant
        this.studentService.deleteStudent(student.id).subscribe(
          () => {
            this.loadStudents(); // Recharger la liste après suppression
            this.notification.success(
              'Success', // Titre de la notification
              'Student deleted successfully!' // Message de la notification
            );
          },
          (error) => {
            this.notification.error(
              'Error', // Titre de la notification
              'Failed to delete student!' // Message de la notification
            );
          }
        );
      },
      nzOnCancel: () => {
        // Si l'utilisateur clique sur "No", ne rien faire
        this.notification.info(
          'Info', // Titre de la notification
          'Deletion cancelled.' // Message de la notification
        );
      }
    });
  }

  onUpdate(student: StudentDtoResponse): void {
    const modalRef = this.modal.create({
      nzTitle: 'Update Student',
      nzContent: StudentComponent,
      nzFooter: null,
      nzData: {
        student: { ...student },
        isUpdate: true
      }
    });
  
    modalRef.afterClose.subscribe((result) => {
      if (result) {
        this.loadStudents();
        this.notification.success('Success', 'Student updated successfully!');
      }
    });
  }
  
  

  openStudentModal(): void {
    // Ouvrir un modal pour créer un nouvel étudiant
    this.modal.create({
      nzTitle: 'Add Student',
      nzContent: StudentComponent, // Utilisez StudentComponent comme contenu du modal
      nzFooter: null,
      nzData: {
        isUpdate: false // Indique que c'est une création
      },
      nzOnOk: () => {
        // Recharger la liste après l'ajout
        this.loadStudents();
      }
    });
  }
}
