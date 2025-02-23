import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzMessageModule, NzMessageService } from 'ng-zorro-antd/message';
import { StudentDtoResponse } from '../../models/student.dto';
import { ClasseDtoResponse } from '../../models/classe.dto';
import { RegistrationService } from '../../services/registration/registration.service';
import { RegistrationDtoRequest } from '../../models/registrations.dto';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registration',
  standalone:true,
  imports: [
    ReactiveFormsModule,
     NzFormModule,
     NzInputModule,
     NzIconModule,
     NzCheckboxModule,
     NzButtonModule,
     NzLayoutModule,
     CommonModule,  
     NzMessageModule,
    NzSelectModule],
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  students: StudentDtoResponse[] = [];
  classes: ClasseDtoResponse[] = [];

  constructor(
    private fb: FormBuilder,
    private registrationService: RegistrationService,
    private message: NzMessageService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.loadStudents();
    this.loadClasses();
  }

  // Initialisation du formulaire
  private initForm(): void {
    this.registrationForm = this.fb.group({
      studentId: [null, Validators.required],
      classeId: [null, Validators.required],
      description: [null, [Validators.required, Validators.minLength(5)]],
      archive: [false]
    });
  }

  // Charger la liste des étudiants
  private loadStudents(): void {
    this.registrationService.getStudents().subscribe(
      (students) => {
        if (students && students.length) {
          this.students = students;
        } else {
          this.message.warning('Aucun étudiant trouvé.');
        }
      },
      (error) => {
        this.message.error('Erreur lors du chargement des étudiants.');
        console.error(error);
      }
    );
  }

 
  private loadClasses(): void {
    this.registrationService.getClasses().subscribe(
      (classes) => {
        if (classes && classes.length) {
          this.classes = classes;
        } else {
          this.message.warning('Aucune classe trouvée.');
        }
      },
      (error) => {
        this.message.error('Erreur lors du chargement des classes.');
        console.error(error);
      }
    );
  }

  // Soumettre le formulaire
  onSubmit(): void {
    if (this.registrationForm.valid) {
      const registrationDto: RegistrationDtoRequest = this.registrationForm.value;
      
      this.registrationService.createRegistration(registrationDto).subscribe(
        (response) => {
          this.message.success(`Enregistrement réussi pour l'étudiant ${response.studentFirstName} ${response.studentLastName} !`);

          this.registrationForm.reset();
        },
        (error) => {
          this.message.error("Erreur lors de l'enregistrement.");
          console.error(error);
        }
      );
    } else {
      this.message.warning("Veuillez remplir correctement le formulaire.");
    }
  }
}
