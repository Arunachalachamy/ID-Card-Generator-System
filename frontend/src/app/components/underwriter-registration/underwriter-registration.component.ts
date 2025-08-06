import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UnderwriterService } from '../../services/underwriter.service';

@Component({
  selector: 'app-underwriter-registration',
  templateUrl: './underwriter-registration.component.html',
  styleUrls: ['./underwriter-registration.component.css']
})
export class UnderwriterRegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
  isLoading: boolean = false;
  generatedId: number = 0;

  constructor(
    private fb: FormBuilder,
    private underwriterService: UnderwriterService,
    private router: Router
  ) {
    this.registrationForm = this.fb.group({
      underwriterId: [{ value: '', disabled: true }],
      name: ['', [Validators.required, Validators.maxLength(30)]],
      dob: ['', Validators.required],
      password: ['', [Validators.required, Validators.maxLength(30)]]
    });
  }

  ngOnInit() {
    this.generateId();
  }

  generateId() {
    // Generate a random ID for display purposes
    this.generatedId = Math.floor(Math.random() * 9000) + 1000;
    this.registrationForm.patchValue({ underwriterId: this.generatedId });
  }

  onSubmit() {
    if (this.registrationForm.valid) {
      this.isLoading = true;
      this.errorMessage = '';
      this.successMessage = '';

      const formData = this.registrationForm.getRawValue();
      formData.underwriterId = this.generatedId;

      this.underwriterService.register(formData).subscribe({
        next: (response) => {
          this.isLoading = false;
          if (response.success) {
            this.successMessage = `UnderWriter registered successfully with ID: ${response.underwriterId}`;
            this.registrationForm.reset();
            this.generateId();
          } else {
            this.errorMessage = response.message;
          }
        },
        error: (error) => {
          this.isLoading = false;
          this.errorMessage = error.error?.message || 'Registration failed. Please try again.';
        }
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin-dashboard']);
  }

  logout() {
    localStorage.removeItem('userRole');
    this.router.navigate(['/']);
  }
}