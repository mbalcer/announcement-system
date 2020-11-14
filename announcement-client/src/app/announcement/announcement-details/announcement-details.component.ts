import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AnnouncementService} from '../../services/announcement.service';

@Component({
  selector: 'app-announcement-details',
  templateUrl: './announcement-details.component.html',
  styleUrls: ['./announcement-details.component.scss']
})
export class AnnouncementDetailsComponent implements OnInit {
  activeAnnouncement: any;

  constructor(private route: ActivatedRoute, private announcementService: AnnouncementService) {
  }

  ngOnInit(): void {
    this.getAnnouncement(this.route.snapshot.paramMap.get('id'));
  }

  getAnnouncement(id: string) {
    this.announcementService.getAnnouncement(Number(id)).subscribe(result => {
      this.activeAnnouncement = result;
    }, error => console.log(error));
  }
}
