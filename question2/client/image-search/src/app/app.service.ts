import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { apiURL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private readonly _http: HttpClient) { }

  search(searchParams: any): Observable<any> {
    return this._http.get(`${apiURL}/image/search`, { params: searchParams });
  }
}
