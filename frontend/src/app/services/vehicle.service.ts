import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from '../models/vehicle.model';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  private baseUrl = 'http://localhost:8080/api/vehicle';

  constructor(private http: HttpClient) { }

  create(vehicle: Vehicle): Observable<any> {
    return this.http.post(`${this.baseUrl}/create`, vehicle);
  }

  getByPolicyId(policyId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/policy/${policyId}`);
  }

  getByVehicleNo(vehicleNo: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/vehicleNo/${vehicleNo}`);
  }

  updateInsuranceType(data: { policyId: number; insuranceType: string }): Observable<any> {
    return this.http.put(`${this.baseUrl}/update-insurance-type`, data);
  }

  renewPolicy(data: { policyId: number; claimStatus: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/renew`, data);
  }

  confirmRenewal(data: { policyId: number; claimStatus: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/confirm-renewal`, data);
  }

  calculatePremium(data: { vehicleType: string; vehicleValue: number }): Observable<any> {
    return this.http.post(`${this.baseUrl}/calculate-premium`, data);
  }
}