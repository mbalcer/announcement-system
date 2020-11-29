import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {AnnouncementService} from '../../services/announcement.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-announcement-details',
  templateUrl: './announcement-details.component.html',
  styleUrls: ['./announcement-details.component.scss']
})
export class AnnouncementDetailsComponent implements OnInit {
  activeAnnouncement: any = null;
  contactMail: any = {
    from: '',
    name: '',
    messageFromUser: '',
    announcement: null
  };

  constructor(private route: ActivatedRoute, private announcementService: AnnouncementService) {
  }

  ngOnInit(): void {
    this.getAnnouncement(this.route.snapshot.paramMap.get('id'));
  }

  getAnnouncement(id: string) {
    this.announcementService.getAnnouncement(Number(id)).subscribe(result => {
      this.activeAnnouncement = result;
      this.contactMail.announcement = result;
    }, error => console.log(error));
  }

  sendEmail(f: NgForm) {
    return false;
  }
}
