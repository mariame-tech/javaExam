import { Injectable } from '@angular/core';
import { ClasseDtoRequest, ClasseDtoResponse } from '../../models/classe.dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClasseService {

  private apiUrl = 'http://localhost:8083/api/classes'; 
  
    constructor(private http: HttpClient) {}
  
  
    saveClasse(classeData: ClasseDtoRequest): Observable<ClasseDtoResponse> {
      return this.http.post<ClasseDtoResponse>(this.apiUrl, classeData);
    }
  
    getAllClasses(): Observable<ClasseDtoResponse[]> {
      return this.http.get<ClasseDtoResponse[]>(this.apiUrl);
    }
  
  
    deleteClasse(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
  
    updateClasse(id: number, classe: ClasseDtoRequest): Observable<ClasseDtoResponse> {
      return this.http.put<ClasseDtoResponse>(`${this.apiUrl}/${id}`, classe);
    }
}
