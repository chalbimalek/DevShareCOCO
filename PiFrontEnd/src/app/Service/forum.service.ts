import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Post} from "./model/Post";

import { Observable } from 'rxjs';
import {Product} from "./model/product";

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  readonly API_URL="http://localhost:8081"
  readonly ENDPOINTS_POST="post"

  constructor(private httpClient: HttpClient) {

  }
    getPost(){
      return this.httpClient.get(this.API_URL+this.ENDPOINTS_POST)
    }



}
