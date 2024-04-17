
import { Rating } from '../model/rating';


import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private baseUrl = 'http://localhost:8081/rating';

  constructor(private http: HttpClient) { }

  addRating(eventId: number, value: number): Observable<Rating> {
    return this.http.post<Rating>(`${this.baseUrl}/add/${eventId}/${value}`, {});
  }

  getAllRatingsForEvent(eventId: number): Observable<Rating[]> {
    return this.http.get<Rating[]>(`${this.baseUrl}/findByEvent/${eventId}`);
  }

  getAverageRatingForEvent(eventId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/EventAverage/${eventId}`);
  }

  getRatingByEvent(eventId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/getRating/${eventId}`);
  }
}
