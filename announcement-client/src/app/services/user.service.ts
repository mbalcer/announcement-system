import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private USER_URL = environment.apiUrl + '/user';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.USER_URL);
  }

  getUserByUsername(username: string): Observable<any> {
    return this.http.get<any>(this.USER_URL + '/username/' + username);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put<any>(this.USER_URL + '/' + user.username, user);
  }

  deleteUser(username: string): Observable<any> {
    return this.http.delete<any>(this.USER_URL + '/' + username);
  }

  changeRole(user: any): Observable<any> {
    return this.http.patch<any>(this.USER_URL + '/role', user);
  }
}
