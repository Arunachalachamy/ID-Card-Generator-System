import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UnderWriter } from '../models/underwriter.model';

@Injectable({
  providedIn: 'root'
})
export class UnderwriterService {
  private baseUrl = 'http://localhost:8080/api/underwriter';

  constructor(private http: HttpClient) { }

  register(underwriter: UnderWriter): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, underwriter);
  }

  search(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/search/${id}`);
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  updatePassword(data: { underwriterId: number; password: string }): Observable<any> {
    return this.http.put(`${this.baseUrl}/update-password`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }

  login(credentials: { underwriterId: number; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials);
  }

  getVehiclesByUnderwriterId(underwriterId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/vehicles/${underwriterId}`);
  }
}