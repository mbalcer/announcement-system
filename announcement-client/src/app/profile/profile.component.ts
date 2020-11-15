import {Component, OnInit, ViewChild} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';
import {AnnouncementService} from '../services/announcement.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatOptionSelectionChange} from '@angular/material/core';
import {PlaceService} from '../services/place.service';
import {CategoryService} from '../services/category.service';
import {MatTabGroup} from '@angular/material/tabs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  displayedColumns: string[] = ['id', 'title', 'price', 'actions'];
  dataSource: MatTableDataSource<any[]>;
  myAnnouncements: any[] = [];

  categories: any[] = [];
  voivodeships: any[] = [];
  places: any[] = [];

  newAnnouncement: any = null;
  editAnnouncement = false;

  @ViewChild('tabs') tabGroup: MatTabGroup;

  constructor(private tokenStorageService: TokenStorageService,
              private announcementService: AnnouncementService,
              private placeService: PlaceService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.clearNewAnnouncement();
    this.categoryService.getAllCategories().subscribe(result => this.categories = result, err => console.log(err));
    this.placeService.getAllVoivodeship().subscribe(result => this.voivodeships = result, err => console.log(err));
    this.getAnnouncements();
  }

  refreshTable() {
    this.dataSource = new MatTableDataSource<any[]>(this.myAnnouncements);
  }

  clearNewAnnouncement() {
    this.newAnnouncement = {
      title: '',
      description: '',
      price: null,
      category: {
        id: null,
        name: ''
      },
      place: {
        id: null,
        voivodeship: {
          id: null,
          name: '',
        },
        city: ''
      },
      user: {
        username: this.getUser().username
      }
    };
  }

  getUser() {
    return this.tokenStorageService.getUser();
  }

  getAnnouncements() {
    this.announcementService.getAnnouncementsByUser(this.getUser().username).subscribe(result => {
      this.myAnnouncements = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  save(form) {
    if (this.editAnnouncement) {
      this.announcementService.putAnnouncement(this.newAnnouncement).subscribe(result => {
        this.clearNewAnnouncement();
        this.refreshTable();
        this.editAnnouncement = false;
      });
    } else {
      this.announcementService.postAnnouncement(this.newAnnouncement).subscribe(result => {
        this.clearNewAnnouncement();
        this.myAnnouncements.push(result);
      }, error => console.log(error));
    }
    form.submitted = false;
  }

  edit(data) {
    this.newAnnouncement = data;
    this.editAnnouncement = true;
    this.tabGroup.selectedIndex = 1;
  }

  delete(data) {
    this.announcementService.deleteAnnouncement(data.id).subscribe(result => {
      this.myAnnouncements.splice(this.myAnnouncements.indexOf(data), 1);
      this.refreshTable();
    }, error => console.log(error));
  }

  setVoivodeship(option: MatOptionSelectionChange) {
    if (option.isUserInput) {
      this.placeService.getAllPlacesByVoivodeship(option.source.value.name).subscribe(result => {
        this.places = result;
      });
    }
  }

  clearVoivodeship() {
    this.newAnnouncement.place = {
      id: null,
      voivodeship: {
        id: null,
        name: '',
      },
      city: ''
    };
    this.places = [];
  }
}
