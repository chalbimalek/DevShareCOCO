import { Component, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from 'src/app/Service/event.service';
import { event1 } from 'src/app/model/event1';
import * as QRCode from 'qrcode';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-details-event',
  templateUrl: './details-event.component.html',
  styleUrls: ['./details-event.component.css']
})
export class DetailsEventComponent {

  @ViewChild('qrcode', { static: false }) qrcode!: ElementRef;

  selectProductIndex = 0;
  product!: event1;
  private ratingSubscription!: Subscription;

  constructor(private activatedRoute: ActivatedRoute, private router : Router,
    private productService: EventService,    private snackbar: MatSnackBar,
  ) { }

  ngOnInit(): void {

    this.product = this.activatedRoute.snapshot.data['product'];
    console.log(this.product);
    
    // Maintenant, vous pouvez accéder à la propriété id_events de l'objet product
    if (this.product) {
      console.log(this.product.id_events);
    } else {
      console.log('Product is undefined');
    }
   
   // this.getproductraitingbyproduct(this.product.id_events);

    this.ratingSubscription = this.productService.getRating(this.product.id_events).subscribe(newRating => {
      this.rating = newRating;
    });


  }
 /* ngOnDestroy(): void {
    this.ratingSubscription.unsubscribe();
  }*/
  
  changeIndex(index:any){
    this.selectProductIndex=index;
  }

  product1: any = {  // Exemple d'objet de produit
    name: 'Nom du produit',
    description: 'Description du produit',
    price: 'Prix du produit',
    address: 'Adresse du produit'
  };



  currentDate: Date = new Date();
  //////////////////////////rating//////////////////
  
/*  saveProductRating(productId: number, rating: number, comment: string): void {
    const productRating: ProductRating = {
      rating: rating,
      comment: comment,
      
    };
          this.productService.saveProductRating(productId, productRating).subscribe(
      response => {
        console.log('Rating saved successfully:', response);
        Swal.fire('Success!', 'Add Comment ', 'success');
        this.rating = 0; 
        this.comment = '';
        this.ratingSelected = false;

      },
      error => {
        console.error('An error occurred while saving rating:', error);
      }
    );
  }
  rating: number = 0; // Note de rating actuelle
  comment: string = '';
  selectedRating: number = 0;

 /*setRating(rating: number): void {
    this.rating = rating;
    this.ratingSelected=true;
    console.log('Rating selected:', this.rating);

  }*/

/*
  productComment: ProductComment[] | null = null; // Initialiser avec une valeur nulle

  getproductraitingbyproduct(id :number) {
    this.productService.getProductRatingByProductId(id).subscribe(
      (data: ProductComment[]) => {
        this.productComment = data;
      },
      error => {
        console.log('Une erreur s\'est produite lors de la récupération des notes de rating du produit:', error);
      }
    );
  }
  openCommentDialog(): void {
    const dialogRef = this.dialog.open(CommentDialogComponent, {
      width: '600px', // Définissez la largeur de la boîte de dialogue selon vos besoins
      data: { productComment: this.productComment } // Transmettez les données des commentaires à afficher
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('La boîte de dialogue des commentaires a été fermée');
    });
  }
  
*/
  ratingSelected: boolean = false;
  rating: number = 0; // Note de rating actuelle
  comment: string = '';
  selectedRating: number = 0;
  /////////////////////////////////
/*  async rateProduct(productId: number, rating: number) {
    try {
      const response = await this.productService.rateProduct(productId, rating).toPromise();
      console.log('Rating saved:', response);
      // Afficher une notification de succès
      Swal.fire('Success!', 'Rating saved successfully.', 'success');
      this.selectedRating = this.rating; // Mettre à jour les étoiles sélectionnées après sauvegarde

    } catch (error) {
      console.error('Error saving rating:', error);
      // Afficher une notification d'erreur
      Swal.fire('Error', 'Failed to save rating. Please try again.', 'error');
    }
  }
  async submitRating(productId: number): Promise<void> {
    if (this.rating === 0) {
      Swal.fire('Error', 'Please select a rating.', 'error');
      return;
    }

    try {
      await this.rateProduct(productId, this.rating);
      // Mettre à jour les étoiles sélectionnées après sauvegarde
      this.selectedRating = this.rating;
    } catch (error) {
      console.error('Error saving rating:', error);
    }
  }
  async commentProduct(productId: number, comment: string) {
    try {
      const response = await this.productService.commentProduct(productId, comment).toPromise();
      console.log('Comment saved:', response);
      // Afficher une notification de succès.
      this.snackbar.open('Comment saved successfully..', 'close', { duration: 5000 });

      //Swal.fire('Success!', 'Comment saved successfully.', 'success');
    } catch (error) {
      console.error('Error saving comment:', error);
      // Afficher une notification d'erreur
      Swal.fire('Error', 'Failed to save comment. Please try again.', 'error');
    }
  }

 

  submitComment(productId:number): void {
    if (this.comment.trim() === '') {
      Swal.fire('Error', 'Please provide a comment.', 'error');
      return;
    }

    // Remplacez par l'ID du produit concerné
    this.productService.commentProduct(productId, this.comment).subscribe(
      (response) => {
        console.log('Comment saved:', response);
        this.snackbar.open('Comment saved successfully..', 'close', { duration: 5000 });

       // Swal.fire('Success!', 'Comment saved successfully.', 'success');
        this.comment = ''; // Réinitialiser le commentaire après avoir sauvegardé
      },
      (error) => {
        console.error('Error saving comment:', error);
        Swal.fire('Error', 'Failed to save comment. Please try again.', 'error');
      }
    );
  }

  */
  setRating(rating: number): void {
    // Enregistrement du rating dans le service et mise à jour de l'affichage
    this.productService.setRating(this.product.id_events, rating);
    this.rating = rating;
    this.ratingSelected = true;
    console.log('Rating selected:', this.rating);
  }

  clearRating(): void {
    // Efface le rating du stockage local et réinitialise l'affichage
    this.productService.clearRating(this.product.id_events);
    this.rating = 0;
  }

  }


  


