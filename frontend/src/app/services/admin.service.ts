import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  login(credentials: { userId: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/admin/login`, credentials);
  }
}