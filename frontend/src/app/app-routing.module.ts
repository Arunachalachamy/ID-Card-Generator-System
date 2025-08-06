import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { UnderwriterLoginComponent } from './components/underwriter-login/underwriter-login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { UnderwriterDashboardComponent } from './components/underwriter-dashboard/underwriter-dashboard.component';
import { UnderwriterRegistrationComponent } from './components/underwriter-registration/underwriter-registration.component';
import { SearchUnderwriterComponent } from './components/search-underwriter/search-underwriter.component';
import { UpdatePasswordComponent } from './components/update-password/update-password.component';
import { DeleteUnderwriterComponent } from './components/delete-underwriter/delete-underwriter.component';
import { ViewAllUnderwritersComponent } from './components/view-all-underwriters/view-all-underwriters.component';
import { ViewVehiclesByUnderwriterComponent } from './components/view-vehicles-by-underwriter/view-vehicles-by-underwriter.component';
import { CreateVehicleInsuranceComponent } from './components/create-vehicle-insurance/create-vehicle-insurance.component';
import { RenewPolicyComponent } from './components/renew-policy/renew-policy.component';
import { UpdatePolicyComponent } from './components/update-policy/update-policy.component';
import { ViewPolicyComponent } from './components/view-policy/view-policy.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  { path: 'underwriter-login', component: UnderwriterLoginComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'underwriter-dashboard', component: UnderwriterDashboardComponent },
  { path: 'underwriter-registration', component: UnderwriterRegistrationComponent },
  { path: 'search-underwriter', component: SearchUnderwriterComponent },
  { path: 'update-password', component: UpdatePasswordComponent },
  { path: 'delete-underwriter', component: DeleteUnderwriterComponent },
  { path: 'view-all-underwriters', component: ViewAllUnderwritersComponent },
  { path: 'view-vehicles-by-underwriter', component: ViewVehiclesByUnderwriterComponent },
  { path: 'create-vehicle-insurance', component: CreateVehicleInsuranceComponent },
  { path: 'renew-policy', component: RenewPolicyComponent },
  { path: 'update-policy', component: UpdatePolicyComponent },
  { path: 'view-policy', component: ViewPolicyComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }