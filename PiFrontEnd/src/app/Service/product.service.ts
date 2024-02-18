import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Media } from '../model/Media';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api'; // L'URL de base de votre backend

  constructor(private httpClient: HttpClient) { }

  addProduct(formData: FormData, mediaList: Media[]): Observable<Product> {

    
    // Utilisez les en-têtes définis pour l'envoi de données
    return this.httpClient.post<Product>(`${this.baseUrl}/addd`,formData);
  }
  public getProductById(pid:number){
    return this.httpClient.get<Product>(`${this.baseUrl}/${pid}`)
  }
  public getAllProduct(){
    return this.httpClient.get<Product[]>(this.baseUrl + "/getall")
  }



  searchProducts(searchTerm: any): Observable<Product[]> {
    // Convertir l'objet searchTerm en chaîne de requête pour l'URL
    let queryString = Object.keys(searchTerm)
      .filter(key => searchTerm[key]) // Exclure les clés vides ou nulles
      .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(searchTerm[key]))
      .join('&');

    // Exécuter la requête HTTP GET avec la chaîne de requête
    return this.httpClient.get<Product[]>(`${this.baseUrl}?${queryString}`);
  }

}

