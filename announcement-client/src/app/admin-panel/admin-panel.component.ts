import {Component, OnInit} from '@angular/core';
import {AnnouncementService} from '../services/announcement.service';
import {MatTableDataSource} from '@angular/material/table';
import {PageEvent} from '@angular/material/paginator';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {
  displayedColumnsAnnouncements: string[] = ['id', 'title', 'user', 'price', 'actions'];
  dataSourceAnnouncements: MatTableDataSource<any[]>;
  announcementPage: any = null;

  displayedColumnsUsers: string[] = ['username', 'email', 'name', 'change-role', 'actions'];
  dataSourceUsers: MatTableDataSource<any[]>;
  users: any[] = [];

  constructor(private announcementService: AnnouncementService, private userService: UserService) { }

  refreshTable() {
    this.dataSourceAnnouncements = new MatTableDataSource<any[]>(this.announcementPage.content);
    this.dataSourceUsers = new MatTableDataSource<any[]>(this.users);
  }

  ngOnInit(): void {
    const param = {
      page: 0,
      size: 10
    };
    this.getAnnouncements(param);
    this.getUsers();
  }

  getAnnouncements(param) {
    this.announcementService.getAllAnnouncements(param).subscribe(result => {
      this.announcementPage = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  deleteAnnouncement(row: any) {
    this.announcementService.deleteAnnouncement(row.id).subscribe(result => {
      this.announcementPage.content.splice(this.announcementPage.content.indexOf(row), 1);
      this.announcementPage.totalElements = this.announcementPage.totalElements - 1;
      this.refreshTable();
    }, error => console.log(error));
  }

  pagination(pageEvent: PageEvent) {
    const param = {
      page: pageEvent.pageIndex,
      size: pageEvent.pageSize
    };
    this.getAnnouncements(param);
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(result => {
      this.users = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  deleteUser(row: any) {
    this.userService.deleteUser(row.username).subscribe(result => {
      this.users.splice(this.users.indexOf(row), 1);
      this.refreshTable();
    }, error => console.log(error));
  }

  changeRole(row: any, newRole: string) {
    const oldUser = row;
    row.role = {
      id: null,
      name: newRole
    };
    this.userService.changeRole(row).subscribe(result => {
      this.users[this.users.indexOf(oldUser)] = result;
    }, error => console.log(error));
  }
}
