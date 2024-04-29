import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient){}
 getevnts() {
    return this.http.get(`http://localhost:8080/api/event/retrieveallevents`);
  }

  addevent(eventData: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/event/Ajouter',
      eventData
    );
  }
  public addFile(formData:FormData,id:any): Observable<any>{
   
    return this.http.post(`http://localhost:8080/api/event/AddeventFile/${id}`,formData, {  reportProgress :true, observe:'events'});
  }
  public retrieveFile(id:any):Observable<Blob>{
    
    const headers = new HttpHeaders().set('Accept', 'application/octet-stream');
    return this.http.get(`http://localhost:8080/api/event/retrieveFile/${id}`,
      { headers : headers,
               responseType: 'blob'
        });
  }


  deletevent(id: any) {
    return this.http.delete(
      'http://localhost:8080/api/event/removeEvent/' + id
    );
  }

  updateevent(id: any, eventData: any): Observable<any> {
    return this.http.put(
      `http://localhost:8080/api/event/modifyEvent/${id}`,
      eventData
    );
  }

  getEventById(id: any) {
    return this.http.get(
      `http://localhost:8080/api/event/events/${id}`
    );
  }

}
