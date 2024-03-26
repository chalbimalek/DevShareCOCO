import { Component, OnInit } from '@angular/core';
import { ProductService } from '../Service/product.service';
import { Router } from '@angular/router';
import Swal, { SweetAlertResult } from 'sweetalert2';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  displayedColumns: string[] = ['Name', 'Description', 'Price','Action'];
  cartDetails : any[] = [];

  constructor(private productService : ProductService,
    private router : Router) { }

  ngOnInit(): void {
    this.getCartDetails();
  }


 getCartDetails(){

      this.productService.getCartDetails().subscribe(
        (response: any) => {
            if(Array.isArray(response)) {
                console.log(response);
                this.cartDetails = response;
            } else {
                console.log("Response is not an array");
            }
        },
        (error) => {
            console.log(error);
        }
    );
  }


  delete(cartId:any){
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
    console.log(cartId)
    this.productService.deleteCartItem(cartId).subscribe(
      (resp) => {
        console.log(resp);
        this.getCartDetails();

      },(error) =>{
        console.log(error);
      }
    )  }
  });
    }
  


  checkout(){
    this.router.navigate(['/buyProduct', {
      isSingleProductCheckout: false, id: 0
    }]);
    // this.productService.getProductDetails(false, 0).subscribe(
    //   (resp) => {
    //     console.log(resp);
    //   },(error) =>{
    //     console.log(error);
    //   }
    // );
  }

  }


