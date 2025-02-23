import { CommonModule } from '@angular/common';
import { Component, Inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';
import { ClasseService } from '../../services/classe/classe.service';
import { ClasseDtoResponse } from '../../models/classe.dto';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-classes',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzIconModule,
    NzCheckboxModule,
    NzButtonModule,
    NzLayoutModule,
    RouterLink,
    CommonModule,
    NzMessageModule],
  templateUrl: './classes.component.html',
  styleUrl: './classes.component.scss'
})
export class ClassesComponent {
   @Input() classe!: ClasseDtoResponse; 
    @Input() isUpdate: boolean = false; 
    isLoading = false;
    classForm!: FormGroup;
  
    constructor(
      private fb: FormBuilder,
      private message: NzMessageService,
      private classeService: ClasseService,
      private modalRef: NzModalRef,
      @Inject(NZ_MODAL_DATA) public data: { student: ClasseDtoResponse; isUpdate: boolean }
    ) {}
  
    ngOnInit(): void {
      this.isUpdate = this.data?.isUpdate ?? false;
      this.classe = this.data?.student ?? {} as ClasseDtoResponse;
  
      this.initForm();
  
      if (this.isUpdate && this.classe) {
        this.classForm.patchValue(this.classe);
      }
    }
  
    private initForm(): void {
      this.classForm = this.fb.group({
        name: ['', Validators.required],
        description: ['', Validators.required],
        archive: [false]
      });
    }
  
    getControl(controlName: string) {
      return this.classForm.get(controlName);
    }
  
    onSubmit(): void {
      if (this.classForm.invalid) {
        this.markFormGroupTouched(this.classForm);
        this.message.error('Please fill out all required fields correctly.', { nzDuration: 3000 });
        return;
      }
  
      this.isLoading = true;
      const studentData = this.classForm.value;
  
      if (this.isUpdate) {
        this.classeService.updateClasse(this.classe.id, studentData).subscribe({
          next: () => {
            this.isLoading = false;
            this.message.success('Student updated successfully!', { nzDuration: 2000 });
            this.modalRef.close({ updated: true, student: studentData });
          },
          error: (error) => {
            this.isLoading = false;
            this.message.error('Failed to update student. Please try again.', { nzDuration: 3000 });
            console.error('Error updating student', error);
          }
        });
      } else {
        this.classeService.saveClasse(studentData).subscribe({
          next: (newClasse) => {
            this.isLoading = false;
            this.message.success('Student created successfully!', { nzDuration: 2000 });
            this.modalRef.close({ created: true, student: newClasse});
          },
          error: (error) => {
            this.isLoading = false;
            this.message.error('Failed to create student. Please try again.', { nzDuration: 3000 });
            console.error('Error creating student', error);
          }
        });
      }
    }
  
    private markFormGroupTouched(formGroup: FormGroup): void {
      Object.values(formGroup.controls).forEach(control => {
        control.markAsTouched();
        if (control instanceof FormGroup) {
          this.markFormGroupTouched(control);
        }
      });
    }

}
