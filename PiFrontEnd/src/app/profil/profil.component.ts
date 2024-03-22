import { Component } from '@angular/core';
import { AuthService } from '../Service/auth.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {
  constructor(private authService: AuthService) {}

  logout() {
    this.authService.logout(); // Appelle la méthode de déconnexion
  }

}
