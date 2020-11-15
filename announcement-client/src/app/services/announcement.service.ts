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

  getAllAnnouncements(param?): Observable<any> {
    return this.http.get<any>(this.ANNOUNCEMENT_URL, {params: param});
  }

  getAllAnnouncementsByFilter(filter: any): Observable<any> {
    let param = {};
    if (filter.page !== undefined && filter.size !== undefined) {
      param = {
        page: filter.page,
        size: filter.size
      };
    }
    if (filter.category.length !== 0 && filter.city.length !== 0) {
      return this.http.get<any>(this.ANNOUNCEMENT_URL + '/category/' + filter.category + '/city/' + filter.city, {params: param});
    } else if (filter.category.length !== 0) {
      return this.http.get<any>(this.ANNOUNCEMENT_URL + '/category/' + filter.category, {params: param});
    } else if (filter.city.length !== 0) {
      return this.http.get<any>(this.ANNOUNCEMENT_URL + '/city/' + filter.city, {params: param});
    } else {
      return this.getAllAnnouncements(param);
    }
  }

  getLatestAnnouncements(limit: number): Observable<any[]> {
    return this.http.get<any[]>(this.ANNOUNCEMENT_URL + '/latest/' + limit);
  }

  getAnnouncement(id: number): Observable<any> {
    return this.http.get<any>(this.ANNOUNCEMENT_URL + '/' + id);
  }

  getAnnouncementsByUser(username: string): Observable<any> {
    return this.http.get<any>(this.ANNOUNCEMENT_URL + '/user/' + username);
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
