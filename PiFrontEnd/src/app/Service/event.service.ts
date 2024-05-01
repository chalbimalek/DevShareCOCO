import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { HttpClient } from '@angular/common/http';
=======
import { HttpClient, HttpHeaders } from '@angular/common/http';
>>>>>>> developer
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient){}
 getevnts() {
<<<<<<< HEAD
    return this.http.get(`http://localhost:8081/api/event/retrieveallevents`);
  }

  addevent(eventData: any): Observable<any> {
    return this.http.post('http://localhost:8081/api/event/Ajouter',
      eventData
    );
  }
=======
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
>>>>>>> developer


  deletevent(id: any) {
    return this.http.delete(
<<<<<<< HEAD
      'http://localhost:8081/api/event/removeEvent/' + id
=======
      'http://localhost:8080/api/event/removeEvent/' + id
>>>>>>> developer
    );
  }

  updateevent(id: any, eventData: any): Observable<any> {
    return this.http.put(
<<<<<<< HEAD
      `http://localhost:8081/api/event/modifyEvent/${id}`,
=======
      `http://localhost:8080/api/event/modifyEvent/${id}`,
>>>>>>> developer
      eventData
    );
  }

  getEventById(id: any) {
    return this.http.get(
<<<<<<< HEAD
      `http://localhost:8081/api/event/events/${id}`
=======
      `http://localhost:8080/api/event/events/${id}`
>>>>>>> developer
    );
  }

}
