import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';
import {AnnouncementService} from '../services/announcement.service';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  displayedColumns: string[] = ['id', 'title', 'price', 'actions'];
  dataSource: MatTableDataSource<any[]>;
  myAnnouncements: any[];

  constructor(private tokenStorageService: TokenStorageService, private announcementService: AnnouncementService) { }

  ngOnInit(): void {
    this.getAnnouncements();
  }

  refreshTable() {
    this.dataSource = new MatTableDataSource<any[]>(this.myAnnouncements);
  }

  getAnnouncements() {
    this.announcementService.getAnnouncementsByUser(this.tokenStorageService.getUser().username).subscribe(result => {
      this.myAnnouncements = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  edit(data) {

  }

  delete(data) {

  }

}
