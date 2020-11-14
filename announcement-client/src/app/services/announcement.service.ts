import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {
  private ANNOUNCEMENT_URL = environment.apiUrl + '/announcement';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getAllAnnouncements(): Observable<any[]> {
    return this.http.get<any[]>(this.ANNOUNCEMENT_URL, this.httpOptions);
  }

  getAllAnnouncementsByFilter(filter: any): Observable<any[]> {
    if (filter.category.length !== 0 && filter.city.length !== 0) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/category/' + filter.category + '/city/' + filter.city, this.httpOptions);
    } else if (filter.category.length !== 0) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/category/' + filter.category, this.httpOptions);
    } else if (filter.city.length !== 0) {
      return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/city/' + filter.city, this.httpOptions);
    } else {
      return this.getAllAnnouncements();
    }
  }

  getLatestAnnouncements(limit: number): Observable<any[]> {
    return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/latest/' + limit);
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
