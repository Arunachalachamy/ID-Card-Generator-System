import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {

  constructor(private router: Router) { }

  navigateToUnderwriterRegistration() {
    this.router.navigate(['/underwriter-registration']);
  }

  navigateToSearchUnderwriter() {
    this.router.navigate(['/search-underwriter']);
  }

  navigateToUpdatePassword() {
    this.router.navigate(['/update-password']);
  }

  navigateToDeleteUnderwriter() {
    this.router.navigate(['/delete-underwriter']);
  }

  navigateToViewAllUnderwriters() {
    this.router.navigate(['/view-all-underwriters']);
  }

  navigateToViewVehiclesByUnderwriter() {
    this.router.navigate(['/view-vehicles-by-underwriter']);
  }

  logout() {
    localStorage.removeItem('userRole');
    this.router.navigate(['/']);
  }
}