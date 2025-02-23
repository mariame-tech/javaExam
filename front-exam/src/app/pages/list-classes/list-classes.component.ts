import { Component } from '@angular/core';
import { ClasseDtoResponse } from '../../models/classe.dto';
import { ClasseService } from '../../services/classe/classe.service';
import { NzModalComponent, NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { ClassesComponent } from '../classes/classes.component';
import { NzTableModule } from 'ng-zorro-antd/table';
import { CommonModule } from '@angular/common';
import { NzIconModule } from 'ng-zorro-antd/icon';

@Component({
  selector: 'app-list-classes',
  standalone: true,
  imports: [NzModalComponent,NzTableModule,CommonModule,NzIconModule],
  templateUrl: './list-classes.component.html',
  styleUrl: './list-classes.component.scss'
})
export class ListClassesComponent {
  classes: ClasseDtoResponse[] = []; 
  
    constructor(
      private classeService: ClasseService,
      private modal: NzModalService,
      private notification: NzNotificationService
    ) {}
  
    ngOnInit(): void {
      this.loadClasses();
    }
  
    loadClasses(): void {
      this.classeService.getAllClasses().subscribe(
        (data) => {
          this.classes = data; 
        },
        (error) => {
          this.notification.error(
            'Error', 
            'Failed to load classes!' // Message de la notification
          );
        }
      );
  
    }
  
    onDelete(classe: ClasseDtoResponse): void {
  
      this.modal.confirm({
        nzTitle: 'Confirmation',
        nzContent: 'Are you sure you want to delete this class?',
        nzOkText: 'Yes',
        nzCancelText: 'No',
        nzOkType: 'primary',
        nzOkDanger: true, 
        nzOnOk: () => {
          
          this.classeService.deleteClasse(classe.id).subscribe(
            () => {
              this.loadClasses(); 
              this.notification.success(
                'Success', 
                'Class deleted successfully!'
              );
            },
            (error) => {
              this.notification.error(
                'Error', 
                'Failed to delete class!' 
              );
            }
          );
        },
        nzOnCancel: () => {
          
          this.notification.info(
            'Info', 
            'Deletion cancelled.' 
          );
        }
      });
    }
  
    onUpdate(student: ClasseDtoResponse): void {
      const modalRef = this.modal.create({
        nzTitle: 'Update Class',
        nzContent: ClassesComponent,
        nzFooter: null,
        nzData: {
          student: { ...student },
          isUpdate: true
        }
      });
    
      modalRef.afterClose.subscribe((result) => {
        if (result) {
          this.loadClasses();
          this.notification.success('Success', 'Class updated successfully!');
        }
      });
    }
    
    
  
    openStudentModal(): void {
      
      this.modal.create({
        nzTitle: 'Add Student',
        nzContent: ClassesComponent, 
        nzFooter: null,
        nzData: {
          isUpdate: false 
        },
        nzOnOk: () => {
          
          this.loadClasses();
        }
      });
    }

}
