import {Component, OnInit, ViewChild} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';
import {AnnouncementService} from '../services/announcement.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatOptionSelectionChange} from '@angular/material/core';
import {PlaceService} from '../services/place.service';
import {CategoryService} from '../services/category.service';
import {MatTabGroup} from '@angular/material/tabs';
import {UserService} from '../services/user.service';
import {NgForm} from '@angular/forms';
import {UploadService} from '../services/upload.service';

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

  fileToUpload: File = null;

  user: any = {
    username: '',
    email: '',
    role: '',
    firstName: '',
    lastName: '',
    phoneNumber: '',
    address: ''
  };

  @ViewChild('tabs') tabGroup: MatTabGroup;

  constructor(private tokenStorageService: TokenStorageService,
              private announcementService: AnnouncementService,
              private placeService: PlaceService,
              private categoryService: CategoryService,
              private userService: UserService,
              private uploadService: UploadService) { }

  ngOnInit(): void {
    this.clearNewAnnouncement();
    this.getUser();
    this.categoryService.getAllCategories().subscribe(result => this.categories = result, err => console.log(err));
    this.placeService.getAllVoivodeship().subscribe(result => this.voivodeships = result, err => console.log(err));
    this.getAnnouncements();
  }

  // tslint:disable-next-line:only-arrow-functions
  public objectComparisonFunction = function( option, value ): boolean {
    return option.id === value.id;
  };
  refreshTable() {
    this.dataSource = new MatTableDataSource<any[]>(this.myAnnouncements);
  }

  clearNewAnnouncement() {
    this.newAnnouncement = {
      title: '',
      description: '',
      price: null,
      photoUrl: '',
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
      user: this.user
    };
  }

  getUser() {
    this.userService.getUserByUsername(this.tokenStorageService.getUser().username).subscribe(result => {
      this.user = result;
      this.newAnnouncement.user = this.user;
    }, error => console.log(error));
  }

  getAnnouncements() {
    this.announcementService.getAnnouncementsByUser(this.tokenStorageService.getUser().username).subscribe(result => {
      this.myAnnouncements = result;
      this.refreshTable();
    }, error => console.log(error));
  }

  getPlacesByVoivodeship(voivodeship: string) {
    this.placeService.getAllPlacesByVoivodeship(voivodeship).subscribe(result => {
      this.places = result;
    });
  }

  uploadFile(form) {
    this.uploadService.uploadFile(this.fileToUpload).subscribe(result => {
      this.newAnnouncement.photoUrl = result.photoUrl;
      this.fileToUpload = null;
      this.save(form);
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
    if (this.newAnnouncement.place.city.length !== 0) {
      this.getPlacesByVoivodeship(this.newAnnouncement.place.voivodeship.name);
    }
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
      this.getPlacesByVoivodeship(option.source.value.name);
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

  updateProfile(form: NgForm) {
    this.userService.updateUser(this.user).subscribe(result => {
      this.user = result;
    }, error => console.log(error));
  }

  onFileSelected(files: FileList) {
    this.fileToUpload = files.item(0);
  }
}
