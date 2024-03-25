import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ProductService } from '../../Service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../model/product';
import * as QRCode from 'qrcode';
import Swal from 'sweetalert2';

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
    private productService: ProductService) { }

  ngOnInit(): void {

   this.product = this.activatedRoute.snapshot.data['product'];
    
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

  @ViewChild('qrcode', { static: false }) qrcode!: ElementRef;

  ngAfterViewInit(): void {
    const qrData = this.constructQRData(this.product); // Méthode pour construire les données du code QR
    this.generateQRCode(qrData);
  }

  constructQRData(product:any): string {
    // Construisez ici les données pour le code QR à partir des informations de la carte
    const productWithoutImage = { ...product };
    delete productWithoutImage.imageModels; 
    return JSON.stringify(productWithoutImage);
  }

  generateQRCode(qrData: string): void {
    QRCode.toCanvas(this.qrcode.nativeElement, qrData, (error) => {
      if (error) {
        console.error('Erreur lors de la génération du code QR:', error);
      }
    });
  }


  currentDate: Date = new Date();

}
  


