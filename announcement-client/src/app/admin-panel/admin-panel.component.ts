import {Component, OnInit} from '@angular/core';
import {AnnouncementService} from '../services/announcement.service';
import {MatTableDataSource} from '@angular/material/table';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.scss']
})
export class AdminPanelComponent implements OnInit {
  displayedColumns: string[] = ['id', 'title', 'user', 'price', 'actions'];
  dataSource: MatTableDataSource<any[]>;
  announcementPage: any = null;

  constructor(private announcementService: AnnouncementService) { }

  refreshTable() {
    this.dataSource = new MatTableDataSource<any[]>(this.announcementPage.content);
  }

  ngOnInit(): void {
    const param = {
      page: 0,
      size: 10
    };
    this.getAnnouncements(param);
  }

  getAnnouncements(param) {
    this.announcementService.getAllAnnouncements(param).subscribe(result => {
      this.announcementPage = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  delete(row: any) {
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
}
