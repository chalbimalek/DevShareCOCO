import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../Service/product.service';
import { Product } from '../../model/product';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ProdutShowDialogComponent } from '../produt-show-dialog/produt-show-dialog.component';
import { ImageProcessingService } from '../../image/image-processing.service';
import { map } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import Swal, { SweetAlertResult } from 'sweetalert2';

@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.css']
})
export class ShowProductDetailsComponent implements OnInit{


constructor(private productservice:ProductService, 
  public imageDialog:MatDialog,
  private imageProcessingService:ImageProcessingService,
  private route:Router){}
productDetails:Product[]=[];
displayedColumns: string[] = ['idProduct', 'Name', 'brand', 'description','images','edit','delete'];


  ngOnInit(): void {
    this.getAllProduct();
  }
  public getAllProduct(){
    this.productservice.getAllProduct(0).
    pipe(
      map((products: Product[],i) => products.map((product: Product) => this.imageProcessingService.createImages(product)))

   ).
    subscribe(
      (resp:Product[])=>{
      console.log(resp);
      this.productDetails=resp;
    }
    );
  }

 public deleteProduct(id : number){
  Swal.fire({
    title: 'Confirmation',
    text: 'Êtes-vous sûr de vouloir supprimer ce produit ?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Oui, supprimer',
    cancelButtonText: 'Annuler'
  }).then((result:SweetAlertResult) => {
    if (result.isConfirmed) {
      this.productservice.deleteProduct(id).subscribe(
        () => {
          console.log("Suppression réussie");
          this.getAllProduct();
          Swal.fire('Success!', 'Produit supprimée avec succès', 'success');

        },
        (error: HttpErrorResponse) => {
          console.log(error);
        }
      );
    }
  });
  }
  showImages(product:Product){
    console.log(product);
    this.imageDialog.open(ProdutShowDialogComponent ,{
      data:{
        images:product.imageModels
    },
      height:'500px',
      width:'800px'
    });
    
  }
  editProduct(id:any){
    this.route.navigate(['back/addproduitBack',{id : id}])
    

  }

  add(){
      this.route.navigate(['back/addproduitBack'])
  }
}
