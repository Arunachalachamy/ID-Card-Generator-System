import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { AdminLoginComponent } from './components/admin-login/admin-login.component';
import { UnderwriterLoginComponent } from './components/underwriter-login/underwriter-login.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { UnderwriterDashboardComponent } from './components/underwriter-dashboard/underwriter-dashboard.component';
import { UnderwriterRegistrationComponent } from './components/underwriter-registration/underwriter-registration.component';
import { SearchUnderwriterComponent, UpdatePasswordComponent, DeleteUnderwriterComponent, ViewAllUnderwritersComponent, ViewVehiclesByUnderwriterComponent, CreateVehicleInsuranceComponent, RenewPolicyComponent, UpdatePolicyComponent, ViewPolicyComponent } from './components/search-underwriter/search-underwriter.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminLoginComponent,
    UnderwriterLoginComponent,
    AdminDashboardComponent,
    UnderwriterDashboardComponent,
    UnderwriterRegistrationComponent,
    SearchUnderwriterComponent,
    UpdatePasswordComponent,
    DeleteUnderwriterComponent,
    ViewAllUnderwritersComponent,
    ViewVehiclesByUnderwriterComponent,
    CreateVehicleInsuranceComponent,
    RenewPolicyComponent,
    UpdatePolicyComponent,
    ViewPolicyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }