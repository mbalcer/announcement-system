import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {
  private PLACE_URL = environment.apiUrl + '/place';
  private VOIVODESHIP_URL = environment.apiUrl + '/voivodeship';

  constructor(private http: HttpClient) { }

  getAllPlaces(): Observable<any[]> {
    return this.http.get<any[]>(this.PLACE_URL);
  }

  getAllPlacesByVoivodeship(voivodeship: string): Observable<any[]> {
    return this.http.get<any[]>(this.PLACE_URL + '/' + voivodeship);
  }

  getAllVoivodeship(): Observable<any[]> {
    return this.http.get<any[]>(this.VOIVODESHIP_URL);
  }
}
