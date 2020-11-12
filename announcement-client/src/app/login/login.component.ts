import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {TokenStorageService} from '../services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {};

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
  }

  signIn() {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.jwt);
        this.tokenStorage.saveUser(data);
      },
      err => {
        console.log(err);
      });
  }

}
