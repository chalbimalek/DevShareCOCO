import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/Service/auth.service';
import { CarppolingServiceService } from 'src/app/Service/carppoling-service.service';
import { notification } from 'src/app/model/Notification';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-notifications-dialog',
  templateUrl: './notifications-dialog.component.html',
  styleUrls: ['./notifications-dialog.component.css']
})
export class NotificationsDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,private authService: AuthService,private productservice:CarppolingServiceService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.notifications = this.trierNotificationsParDate(this.notifications);

  
    this.loadNotifications();
    // Récupérer le nom d'utilisateur après avoir chargé les notifications
    this.loggedInUser = this.authService.getUserIdFromToken();
           console.log("ddddddddddddd "+this.loggedInUser);
           
       }
       trierNotificationsParDate(notifications: any[]): any[] {
        return notifications.sort((a, b) => {
          const dateA = new Date(a.timestamp).getTime();
          const dateB = new Date(b.timestamp).getTime();
          return dateB - dateA; // Tri décroissant pour avoir les dates les plus récentes en premier
        });
      }
  notifications: notification[] = [];
     loggedInUser: string | null = null;
 
     
     loadNotifications(): void {
       this.productservice.getNotificationsForUser().subscribe(
         notifications => {
           this.notifications = notifications;
           console.log(notifications);
           console.log("username  dddddddd "+this.loggedInUser);
 
           
         },
         error => {
           console.error('Erreur lors de la récupération des notifications :', error);
           // Gérez l'erreur ici, par exemple afficher un message à l'utilisateur
         }
       );
     }
     accepterCovoiturage(carpoolingId: number, userId: number): void {
      if (!carpoolingId || !userId) {
        console.error('ID de covoiturage ou ID utilisateur non valide :', carpoolingId, userId);
        console.log(userId);
        
        return;
      }
    
      this.productservice.accepterOuRefuserCovoiturage(carpoolingId, true, userId)
        .subscribe(response => {
          console.log(userId);
          Swal.fire('Success!', 'User accepte avec succès', 'success');
          console.log('Covoiturage accepté avec succès :', response);
          // Traitez la réponse ici
        }, error => {
          console.error('Erreur lors de l\'acceptation du covoiturage :', error);
          Swal.fire('Success!', 'User accepte avec succès', 'success');

          // Gérez l'erreur ici
        });
    }
  
    annulerAcceptationCovoiturage(carpoolingId: number, userId: number): void {
      if (!carpoolingId || !userId) {
        console.error('ID de covoiturage ou ID utilisateur non valide :', carpoolingId, userId);
        console.log(userId);
        
        return;
      }
    
      this.productservice.annulerAcceptationCovoiturage(carpoolingId, userId)
        .subscribe(response => {
          console.log(userId);
          Swal.fire('Success!', 'User Annuler avec succès', 'success');

    
          console.log('Covoiturage annule avec succès :', response);
          // Traitez la réponse ici
        }, error => {
          console.error('Erreur lors de l\'acceptation du covoiturage :', error);
          Swal.fire('Success!', 'User Annuler avec succès', 'success');

          // Gérez l'erreur ici
        });
    }
    
    refuserCovoiturage(carpoolingId: number, userId: number): void {
      if (!carpoolingId || !userId) {
        console.error('ID de covoiturage ou ID utilisateur non valide :', carpoolingId, userId);
        return;
      }
    
      this.productservice.accepterOuRefuserCovoiturage(carpoolingId, false, userId)
        .subscribe(response => {
          console.log('Covoiturage refusé avec succès :', response);
          Swal.fire('Success!', 'User refusee avec succès', 'success');

          // Traitez la réponse ici
        }, error => {
          console.error('Erreur lors du refus du covoiturage :', error);
          Swal.fire('Success!', 'User refusee avec succès', 'success');

          // Gérez l'erreur ici
        });
    }

}
