import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, map, tap } from 'rxjs';
import { OrderDetails } from '../model/OrderDetails';
import { MyOrderDetails } from '../model/MyOrderDetails';
import { Category } from '../model/enumerations/Category';
import { AuthService } from './auth.service';
import { ProductRating } from '../model/ProductRating';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api'; // L'URL de base de votre backend

  constructor(private authservice:AuthService, private httpClient: HttpClient) { }



  addProduct(productData: FormData){

        // Utilisez les en-têtes définis pour l'envoi de données
    return this.httpClient.post<Product>(`${this.baseUrl}/addd`,productData);
  }
  public getProductById(pid:number){
    return this.httpClient.get<Product>("http://localhost:8080/api/"+pid)
  }
  public getAllProduct(pageNumber:any){
    return this.httpClient.get<Product[]>(this.baseUrl + "/getall?pageNumber="+pageNumber)
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
  public deleteProduct(id:number){
   return this.httpClient.delete("http://localhost:8080/api"+"/delete/"+id);
    }

    public addToCart(productId:any){
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get("http://localhost:8080/api/addToCart/"+productId, { headers });
     }

     public getCartDetails(){
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get("http://localhost:8080/api/getCartDetails", { headers });
     }

     public getProductDetails(isSingeProductCheckout:any,productId:any){
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get<Product[]>("http://localhost:8080/api/getProductDetails/"+isSingeProductCheckout+"/"+productId, { headers });
     }
  
     public placeOrder(orderDetails: OrderDetails, isCartCheckout:any){
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.post("http://localhost:8080/api/placeOrder/"+isCartCheckout, orderDetails, { headers });
     }
     public getAllOrderDetailsForAdmin() : Observable<MyOrderDetails[]>{
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get<MyOrderDetails[]>("http://localhost:8080/api/getAllOrderDetails", { headers });
     }
  
    public getMyOrders() : Observable<MyOrderDetails[]>{
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get<MyOrderDetails[]>("http://localhost:8080/api/getOrderDetails", { headers });
     }
  
    public deleteCartItem(cartId:any){
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.delete("http://localhost:8080/api/deleteCartItem/"+cartId, { headers });
     }
  
     markOrderAsDelivered(orderId: any): Observable<any> {
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.get("http://localhost:8080/api/markOrderAsDelivered/" + orderId, { headers })
        .pipe(
          tap(() => {
            this.orderDeliveredSubject.next(true); // Mettre à jour l'état de la commande livrée
          })
        );
    }
  
    getOrderDeliveredState(): Observable<boolean> {
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.orderDeliveredSubject.asObservable();
    }
     private orderDeliveredSubject = new BehaviorSubject<boolean>(false);
     ///////////
     getProductsByCategory(category: Category): Observable<Product[]> {
      return this.httpClient.get<Product[]>(this.baseUrl+"/products", { params: { category: category } });
    }

    getMostPurchasedCategory(): Observable<string[]> {
      return this.httpClient.get<string[]>("http://localhost:8080/api/statistics/most-purchased-category");
    }
    
    saveProductRating(productId: number, productRating: ProductRating): Observable<ProductRating> {
      const token = this.authservice.getToken();
      let headers = new HttpHeaders();
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
      return this.httpClient.post<ProductRating>("http://localhost:8080/api/api/product-ratings/"+productId, productRating, { headers });
    }
    
    getProductRatingByProductId(productId: number): Observable<ProductRating[]> {
      return this.httpClient.get<ProductRating[]>("http://localhost:8080/api/api/product-ratings/product/"+productId);
    }

    statistiqueRating():Observable<string[]>{
      return this.httpClient.get<string[]>("http://localhost:8080/api/api/product-ratings/statistiqueRating");
    }
}

