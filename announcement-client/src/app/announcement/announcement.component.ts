import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AnnouncementService} from '../services/announcement.service';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.scss']
})
export class AnnouncementComponent implements OnInit {
  announcementPage: any;
  filterModel = {
    category: '',
    city: '',
    page: 0,
    size: 10
  };

  constructor(private route: ActivatedRoute, private announcementService: AnnouncementService) { }

  ngOnInit(): void {
    if (this.route.snapshot.queryParams.category || this.route.snapshot.queryParams.city) {
      this.route.queryParams.subscribe(params => {
        this.filterModel = {
          category: params.category,
          city: params.city,
          page: this.filterModel.page,
          size: this.filterModel.size
        };
        this.getAnnouncements();
      });
    } else {
      this.getAnnouncements();
    }
  }

  getAnnouncements() {
    this.announcementService.getAllAnnouncementsByFilter(this.filterModel).subscribe(result => {
      this.announcementPage = result;
    }, error => console.log(error));
  }

  pagination(pageEvent: PageEvent) {
    this.filterModel = {
      category: this.filterModel.category,
      city: this.filterModel.city,
      page: pageEvent.pageIndex,
      size: pageEvent.pageSize
    };
    this.getAnnouncements();
  }

}
