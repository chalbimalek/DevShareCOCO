import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ProductService } from '../../Service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../model/product';
import * as QRCode from 'qrcode';
import Swal from 'sweetalert2';
import { ProductRating } from 'src/app/model/ProductRating';
import { QRDialogComponent } from 'src/app/qrdialog/qrdialog.component';
import { MatDialog } from '@angular/material/dialog';
import { CommentDialogComponent } from '../comment-dialog/comment-dialog.component';

@Component({
  selector: 'app-detaitlsback',
  templateUrl: './detaitlsback.component.html',
  styleUrls: ['./detaitlsback.component.css']
})
export class DetaitlsbackComponent implements OnInit {
addToCart(productId: any) {

   this.productService.addToCart(productId).subscribe(
    
    
      (response) => {
        console.log(response);
        Swal.fire('Success!', 'Produit ajouté avec succès dans le cart', 'success');

      },(error) => {
        console.log(error)
      }
    )
    console.log(productId);}

  selectProductIndex = 0;
  product!: Product;

  constructor(private activatedRoute: ActivatedRoute, private router : Router,
    private productService: ProductService,public dialog: MatDialog) { }

  ngOnInit(): void {

   this.product = this.activatedRoute.snapshot.data['product'];
    this.getproductraitingbyproduct(this.product.idProduct);
  }

  changeIndex(index:any){
    this.selectProductIndex=index;
  }

  product1: any = {  // Exemple d'objet de produit
    name: 'Nom du produit',
    description: 'Description du produit',
    price: 'Prix du produit',
    address: 'Adresse du produit'
  };


  openQRDialog(): void {
    const dialogRef = this.dialog.open(QRDialogComponent, {
      width: '300px',
      data: { product: this.product } // Passer l'URL de votre code QR à afficher
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('La boîte de dialogue a été fermée');
    });
  }

  currentDate: Date = new Date();
  //////////////////////////rating//////////////////
  
  saveProductRating(productId: number, rating: number, comment: string): void {
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

  setRating(rating: number): void {
    this.rating = rating;
    this.ratingSelected=true;
  }


  productRating: ProductRating[] | null = null; // Initialiser avec une valeur nulle

  getproductraitingbyproduct(id :number) {
    this.productService.getProductRatingByProductId(id).subscribe(
      (data: ProductRating[]) => {
        this.productRating = data; // Stockez les données dans la variable productRating
      },
      error => {
        console.log('Une erreur s\'est produite lors de la récupération des notes de rating du produit:', error);
      }
    );
  }
  
  openCommentDialog(): void {
    const dialogRef = this.dialog.open(CommentDialogComponent, {
      width: '600px', // Définissez la largeur de la boîte de dialogue selon vos besoins
      data: { productRating: this.productRating } // Transmettez les données des commentaires à afficher
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('La boîte de dialogue des commentaires a été fermée');
    });
  }
  

  ratingSelected: boolean = false;

  }


  


