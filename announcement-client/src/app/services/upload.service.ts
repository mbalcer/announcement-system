import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private UPLOAD_URL = environment.apiUrl + '/upload';

  constructor(private http: HttpClient) { }

  uploadFile(file: File): Observable<any> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    return this.http.post<any>(this.UPLOAD_URL, formdata);
  }
}
