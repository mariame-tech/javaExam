import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { StudentService } from '../../services/student/student.service';
import { NZ_MODAL_DATA, NzModalRef } from 'ng-zorro-antd/modal'; 
import { StudentDtoResponse } from '../../models/student.dto';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzIconModule,
    NzCheckboxModule,
    NzButtonModule,
    NzLayoutModule,
    CommonModule,
    NzMessageModule
  ],
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {

  @Input() student!: StudentDtoResponse; 
  @Input() isUpdate: boolean = false; 
  isLoading = false;
  registrationForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private studentService: StudentService,
    private modalRef: NzModalRef,
    @Inject(NZ_MODAL_DATA) public data: { student: StudentDtoResponse; isUpdate: boolean }
  ) {}

  ngOnInit(): void {
    this.isUpdate = this.data?.isUpdate ?? false;
    this.student = this.data?.student ?? {} as StudentDtoResponse;

    this.initForm();

    if (this.isUpdate && this.student) {
      this.registrationForm.patchValue(this.student);
    }
  }

  private initForm(): void {
    this.registrationForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailPro: ['', [Validators.required, Validators.email]],
      emailPerso: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{8,15}$/)]],
      address: ['', Validators.required],
      registrationNu: ['', Validators.required],
      archive: [false]
    });
  }

  getControl(controlName: string) {
    return this.registrationForm.get(controlName);
  }

  onSubmit(): void {
    if (this.registrationForm.invalid) {
      this.markFormGroupTouched(this.registrationForm);
      this.message.error('Please fill out all required fields correctly.', { nzDuration: 3000 });
      return;
    }

    this.isLoading = true;
    const studentData = this.registrationForm.value;

    if (this.isUpdate) {
      this.studentService.updateStudent(this.student.id, studentData).subscribe({
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
      this.studentService.registerStudent(studentData).subscribe({
        next: (newStudent) => {
          this.isLoading = false;
          this.message.success('Student created successfully!', { nzDuration: 2000 });
          this.modalRef.close({ created: true, student: newStudent });
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
