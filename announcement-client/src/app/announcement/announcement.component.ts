import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AnnouncementService} from '../services/announcement.service';

@Component({
  selector: 'app-announcement',
  templateUrl: './announcement.component.html',
  styleUrls: ['./announcement.component.scss']
})
export class AnnouncementComponent implements OnInit {
  announcements: any[] = [];

  constructor(private route: ActivatedRoute, private announcementService: AnnouncementService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.getAnnouncements(params);
    });
  }

  getAnnouncements(filter: any) {
    this.announcementService.getAllAnnouncementsByFilter(filter).subscribe(result => {
      this.announcements = result;
      console.log(result);
    }, error => console.log(error));
  }

}
