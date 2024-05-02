import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams  } from '@angular/common/http';
import { AnnonceCollocation } from '../model/AnnonceCollocation';
import {Observable, catchError} from "rxjs";
@Injectable({
    providedIn: 'root',
  })
  export class AnnonceCollocationService {
    
    constructor(private httpClient: HttpClient){}
      getAnnonceCollocations(page: number, size: number): Observable<AnnonceCollocation[]> {
        return this.httpClient.get<AnnonceCollocation[]>(`http://localhost:8081/api/collcation/Paggination/${page}/${size}`);
      }
      SendRequestMeet(id: number, message:string): Observable<AnnonceCollocation> {
        return this.httpClient.put<AnnonceCollocation>(`http://localhost:8081/api/collcation/SmSMeet/${id}/${message}`, null);
      }
      getAllAnnonceCollocation() {
        return this.httpClient.get<AnnonceCollocation>('http://localhost:8081/api/collcation/all');
      }
      updateAnnonceCollocation(annonceCollocation:AnnonceCollocation): Observable<AnnonceCollocation> {
        return this.httpClient.post<AnnonceCollocation>('http://localhost:8081/api/collcation/update', annonceCollocation);
      }
      addAnnonceCollocation(annonceCollocation:AnnonceCollocation): Observable<AnnonceCollocation> {
        return this.httpClient.post<AnnonceCollocation>('http://localhost:8081/api/collcation/add', annonceCollocation);
      }

      Delete(id: number) {
        return this.httpClient.delete(`http://localhost:8081/api/collcation/${id}`);
      }
  }