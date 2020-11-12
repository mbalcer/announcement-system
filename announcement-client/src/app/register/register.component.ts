import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../login/login.component.scss', './register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  isSuccessfulRegister = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  signUp(form) {
    this.authService.register(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessfulRegister = true;
        this.errorMessage = '';
        this.form = {};
        form.submitted = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSuccessfulRegister = false;
      }
    );

  }

}
