import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MailService {
  private MAIL_URL = environment.apiUrl + '/mail';

  constructor(private http: HttpClient) { }

  sendEmail(contactMail: any): Observable<boolean> {
    return this.http.post<boolean>(this.MAIL_URL, contactMail);
  }
}
