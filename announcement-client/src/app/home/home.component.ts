import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {PlaceService} from '../services/place.service';
import {MatOptionSelectionChange} from '@angular/material/core';
import {AnnouncementService} from '../services/announcement.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  categories: any[] = [];
  places: any[] = [];
  voivodeships: any[] = [];
  announcements: any[] = [];
  searchModel: any = {
    category: '',
    voivodeship: '',
    place: ''
  };

  constructor(private categoryService: CategoryService,
              private placeService: PlaceService,
              private announcementService: AnnouncementService) { }

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe(result => this.categories = result, err => console.log(err));
    this.placeService.getAllVoivodeship().subscribe(result => this.voivodeships = result, err => console.log(err));
    this.getAnnouncements();
  }

  search() {
    console.log(this.searchModel);
  }

  setVoivodeship(option: MatOptionSelectionChange) {
    if (option.isUserInput) {
      this.placeService.getAllPlacesByVoivodeship(option.source.value).subscribe(result => {
        this.places = result;
      });
    }
  }

  clearVoivodeship() {
    this.searchModel.voivodeship = '';
    this.searchModel.place = '';
    this.places = [];
  }

  getAnnouncements() {
    this.announcementService.getLatestAnnouncements(9).subscribe(result => this.announcements = result);
  }
}
