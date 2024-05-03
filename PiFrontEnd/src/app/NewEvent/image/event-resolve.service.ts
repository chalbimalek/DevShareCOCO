import { Injectable } from '@angular/core';
import { EventProcessingService } from './event-processing.service';
import { EventService } from 'src/app/Service/event.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { event1 } from 'src/app/model/event1';
import { Observable, catchError, map, of } from 'rxjs';
import { Type_Event } from 'src/app/model/enumerations/Type_Event';

@Injectable({
  providedIn: 'root'
})
export class EventResolveService {

  constructor(private image: EventProcessingService, private productService: EventService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<event1> {
    const id = route.paramMap.get("id");
    if (id) {
      const productId = parseInt(id, 10);
      return this.productService.getProductById(productId).pipe(
       map(p => (this.image.createImages(p))),
        catchError(error => {
          console.error("Error fetching product details:", error);
          return of(this.getProductDetails()); // Retourner un produit vide en cas d'erreur
        })
      );
    } else {
      return of(this.getProductDetails());
    }
  }

  getProductDetails(): event1 {
    return {
      id_events:0,
      title:"",
      description:"",
      startDate:new Date(),
      endDate:new Date(),
      location_event:"",
      price:0,
      type_Event:Type_Event.ADEVERTISSMENT,
      created:new Date(),
      imageModels:[],
    };
  }
}



