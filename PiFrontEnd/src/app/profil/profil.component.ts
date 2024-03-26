import { Component } from '@angular/core';
import { AuthService } from '../Service/auth.service';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {
  constructor(private authService: AuthService,private matDialog: MatDialog,) {}

  logout() {
    this.authService.logout(); // Appelle la méthode de déconnexion
  }
  
}
