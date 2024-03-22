import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/api';
  private jwtHelper = new JwtHelperService();

  constructor(private http: HttpClient, private router: Router) {}

  register(signupRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/signup`, signupRequest);
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/signin`, loginRequest).pipe(
      map((response: any) => {
        if (response && response.accessToken) {
          localStorage.setItem('accessToken', response.accessToken);
          console.log('Token retrieved and stored successfully:', response.accessToken);
        } else {
        
          console.log('No token found in the response:', response);
        }
        return response;
      }),
      catchError((error) => {
        console.error('Error during login:', error);
        return throwError(error);
      })
    );
  }
  
  logout() {
    console.log("Début de la déconnexion");
    if (localStorage.getItem('accessToken')) {
      localStorage.removeItem('accessToken');
      console.log("Jeton d'authentification supprimé avec succès");
    }
    this.router.navigate(['/login']);
    console.log("Redirection vers la page de connexion après déconnexion");
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('accessToken');
    console.log("isAuthenticated suceess");
    
    return !!token && !this.jwtHelper.isTokenExpired(token);
  }

  isUserLoggedIn(): boolean {
    console.log("boo true");
    
    return this.isAuthenticated();
  }
  getUserIdFromToken(): string | null {
    const token = localStorage.getItem('accessToken');
    if (token) {
      try {
        // Décoder le token JWT pour obtenir les informations sur l'utilisateur
        const decodedToken: any = jwt_decode(token);
        // Récupérer l'ID de l'utilisateur à partir du token décrypté
        const userId = decodedToken.sub;
        return userId;
      } catch (error) {
        console.error('Erreur lors du décryptage du token JWT:', error);
        return null;
      }
    } else {
      return null;
    }
  }
 /* private isAuthenticatedValue: boolean = false;

  isAuthenticated(): boolean {
    return this.isAuthenticatedValue; // Accès direct à la propriété, pas de parenthèses
  }

  setAuthenticated(isAuthenticated: boolean): void {
    this.isAuthenticatedValue = isAuthenticated;
  }*/

}
function jwt_decode(token: string): any {
  throw new Error('Function not implemented.');
}

