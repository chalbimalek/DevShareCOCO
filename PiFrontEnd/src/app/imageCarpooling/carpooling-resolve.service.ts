import { Injectable } from '@angular/core';
import { Carpooling } from '../model/carpooling';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { CarpoolingProcessingService } from './carpooling-processing.service';
import { CarppolingServiceService } from '../Service/carppoling-service.service';
import { Observable, catchError, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarpoolingResolveService implements Resolve<Carpooling>{

  constructor(private image: CarpoolingProcessingService, private productService: CarppolingServiceService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Carpooling> {
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

  getProductDetails(): Carpooling {
    return {
      idCarpolling:0,
      pointArrivee: "",
      name: "",
      pointSorite: "",
      price: 0,
      adresse:"",
      description: "",
      nbrPlaceDisponible: 0,
      gouvernorat:"",
      numero:0,
      imageModels: [],
      DateSortie:new Date(2024, 2, 19, 12, 0, 0) 
    };
  }
}

