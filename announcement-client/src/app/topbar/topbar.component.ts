import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent implements OnInit {
  loggedUser: boolean;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.loggedUser = this.tokenStorageService.isLogged();
  }

}
