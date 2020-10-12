import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { apiURL } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private readonly _http: HttpClient) { }

  save(uploadData: FormData): Observable<any> {
    return this._http.post(`${apiURL}/image`, uploadData);
  }
}
