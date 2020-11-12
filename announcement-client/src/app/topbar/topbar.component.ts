import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {
  loggedUser: boolean;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    this.loggedUser = this.tokenStorageService.isLogged();
  }

  logout() {
    this.tokenStorageService.signOut();
    this.loggedUser = false;
    this.router.navigateByUrl('/');
  }
}
