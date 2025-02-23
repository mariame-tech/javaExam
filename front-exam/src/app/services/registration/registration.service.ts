import { Injectable } from '@angular/core';
import { RegistrationDtoRequest, RegistrationDtoResponse } from '../../models/registrations.dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudentDtoResponse } from '../../models/student.dto';
import { ClasseDtoResponse } from '../../models/classe.dto';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private registrationApiUrl = 'http://localhost:8083/api/registrations'; 
  private studentApiUrl = 'http://localhost:8083/api/students'; 
  private classeApiUrl = 'http://localhost:8083/api/classes'; 

  constructor(private http: HttpClient) {}

  getStudents(): Observable<StudentDtoResponse[]> {
    return this.http.get<StudentDtoResponse[]>(this.studentApiUrl);
  }

  getClasses(): Observable<ClasseDtoResponse[]> {
    return this.http.get<ClasseDtoResponse[]>(this.classeApiUrl);
  }

  createRegistration(registration: RegistrationDtoRequest): Observable<RegistrationDtoResponse> {
    return this.http.post<RegistrationDtoResponse>(this.registrationApiUrl, registration);
  }

  getAllRegistrations(): Observable<RegistrationDtoResponse[]> {
    return this.http.get<RegistrationDtoResponse[]>(this.registrationApiUrl);
  }

  deleteRegistration(id: number): Observable<void> {
    return this.http.delete<void>(`${this.registrationApiUrl}/${id}`);
  }

  updateRegistration(id: number, registration: RegistrationDtoRequest): Observable<RegistrationDtoResponse> {
    return this.http.put<RegistrationDtoResponse>(`${this.registrationApiUrl}/${id}`, registration);
  }
}
