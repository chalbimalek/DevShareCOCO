import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient){}
 getevnts() {
    return this.http.get(`http://localhost:8081/api/event/retrieveallevents`);
  }

  addevent(eventData: any): Observable<any> {
    return this.http.post('http://localhost:8081/api/event/Ajouter',
      eventData
    );
  }


  deletevent(id: any) {
    return this.http.delete(
      'http://localhost:8081/api/event/removeEvent/' + id
    );
  }

  updateevent(id: any, eventData: any): Observable<any> {
    return this.http.put(
      `http://localhost:8081/api/event/modifyEvent/${id}`,
      eventData
    );
  }

  getEventById(id: any) {
    return this.http.get(
      `http://localhost:8081/api/event/events/${id}`
    );
  }

}
