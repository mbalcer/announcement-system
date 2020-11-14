import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {
  private ANNOUNCEMENT_URL = environment.apiUrl + '/announcement';

  constructor(private http: HttpClient) { }

  getAllAnnouncements(): Observable<any[]> {
    return this.http.get<any[]>(this.ANNOUNCEMENT_URL);
  }

  getAllAnnouncementsByFilter(filter: any): Observable<any[]> {
    if (filter.category && filter.place) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/category/' + filter.category + '/place/' + filter.place);
    } else if (filter.category) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/category/' + filter.category);
    } else if (filter.place) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/place/' + filter.place);
    } else {
      return this.getAllAnnouncements();
    }
  }

  getAnnouncement(id: number): Observable<any> {
    return this.http.get<any>(this.ANNOUNCEMENT_URL + '/' + id);
  }

  postAnnouncement(announcement: any): Observable<any> {
    return this.http.post<any>(this.ANNOUNCEMENT_URL, announcement);
  }

  putAnnouncement(announcement: any): Observable<any> {
    return this.http.put<any>(this.ANNOUNCEMENT_URL + '/' + announcement.id, announcement);
  }

  deleteAnnouncement(id: number): Observable<any> {
    return this.http.delete<any>(this.ANNOUNCEMENT_URL + '/' + id);
  }
}
