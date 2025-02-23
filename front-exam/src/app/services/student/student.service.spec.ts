import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudentDtoRequest, StudentDtoResponse } from '../models/student.dto'; // Importez les interfaces

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private apiUrl = 'http://localhost:8083/api/students'; // URL de votre backend

  constructor(private http: HttpClient) {}

  // Méthode pour enregistrer un étudiant
  registerStudent(studentData: StudentDtoRequest): Observable<StudentDtoResponse> {
    return this.http.post<StudentDtoResponse>(this.apiUrl, studentData);
  }
}
