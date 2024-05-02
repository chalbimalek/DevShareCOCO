import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams  } from '@angular/common/http';
import { RendezVous } from '../model/RendezVous';
import {Observable, catchError} from "rxjs";
@Injectable({
    providedIn: 'root',
  })
  export class RendezVousService {

    constructor(private httpClient: HttpClient){}

    findbyOwner(id:number) {
        return this.httpClient.get<RendezVous[]>(`http://localhost:8081/api/RendezVous/findbyOwner/${id}`);
      }
      findbyOwnerOrclient(id:number) {
        return this.httpClient.get<RendezVous[]>(`http://localhost:8081/api/RendezVous/findbyOwnerOrclient/${id}`);
      }
      findbyannoance(id:number) {
        return this.httpClient.get<RendezVous[]>(`http://localhost:8081/api/RendezVous/findbyannoance/${id}`);
      }
      addRendezVous(r:RendezVous,idu:number,idc:number): Observable<RendezVous> {
        return this.httpClient.post<RendezVous>(`http://localhost:8081/api/RendezVous/add/${idu}/${idc}`, r);
      }
      Accaepte(id: number): Observable<RendezVous> {
        return this.httpClient.put<RendezVous>(`http://localhost:8081/api/RendezVous/accaepte/${id}`, null);
      }
      Refuse(id: number) {
        return this.httpClient.delete(`http://localhost:8081/api/RendezVous/Refuse/${id}`);
      }
      remove(id: number) {
        return this.httpClient.delete(`http://localhost:8081/api/RendezVous/remove/${id}`);
      }
  }
