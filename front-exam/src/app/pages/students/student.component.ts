import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';

import { CommonModule } from '@angular/common';
import { StudentService } from '../../services/student/student.service';

@Component({
  selector: 'app-register',
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
    NzMessageModule
  ],
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit{

 
  isLoading = false;
  registrationForm: FormGroup = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    emailPro: ['', [Validators.required, Validators.email]],
    emailPerso: ['', [Validators.required, Validators.email]],
    phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{8,15}$/)]],
    address: ['', Validators.required],
    registrationNu: ['', Validators.required],
    archive: [false]
  });

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private message: NzMessageService,
    private studentService: StudentService 
  ) {}
  
  getControl(controlName: string) {
    return this.registrationForm.get(controlName);
  }

  ngOnInit(): void {
   
  }


  onSubmit(){
    if (this.registrationForm.invalid) {
      this.markFormGroupTouched(this.registrationForm);
      this.message.error('Please fill out all required fields correctly.', {
        nzDuration: 3000
      });
      return;
    }
    this.isLoading = true;

    this.studentService.registerStudent(this.registrationForm.value).subscribe({
      next: (response) => {
        console.log('Registration successful', response);
        this.isLoading = false;

        this.message.success('Registration successful! Redirecting to login page...', {
          nzDuration: 2000
        });

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error) => {
        console.error('Registration failed', error);
        this.isLoading = false;

        this.message.error('Registration failed. Please try again.', {
          nzDuration: 3000
        });
      }
    });
    
  }
  private markFormGroupTouched(formGroup: FormGroup) {
    Object.values(formGroup.controls).forEach(control => {
      control.markAsTouched();
      if (control instanceof FormGroup) {
        this.markFormGroupTouched(control);
      }
    });

  }
}
