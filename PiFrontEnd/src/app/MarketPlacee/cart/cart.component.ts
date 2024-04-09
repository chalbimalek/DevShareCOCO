import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../Service/product.service';
import { Router } from '@angular/router';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { CartDetail } from 'src/app/model/CartDetail';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  product!:Product;
  displayedColumns: string[] = ['Name', 'Description', 'Price','Action'];
  cartDetails : CartDetail[] = [];

  constructor(private productService : ProductService,
    private router : Router) { }

  ngOnInit(): void {
    this.getCartDetails();
  }


  getCartDetails() {
    this.productService.getCartDetails().subscribe(
      (response: CartDetail[]) => {
        this.cartDetails = response;
        console.log(this.cartDetails);
         // Vérifiez la structure des données dans la console
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
  
    checkout1(productId: number): void {
      // Naviguer vers la page de checkout avec l'ID du produit sélectionné
      this.router.navigate(['/buyProduct', {
        isSingleProductCheckout: true,
        id: productId
      }]);
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
  deleteProductFromCart(productId: number) {
    if (productId) { // Vérifiez d'abord si productId est défini
      this.productService.deleteProductFromCart(productId).subscribe(
        response => {
          console.log('Product deleted from cart successfully');
       

          // Mettez à jour votre liste de produits de panier ou effectuez d'autres actions nécessaires ici
        },
        error => {
          console.error('Error deleting product from cart:', error);
        }
      );
    } else {
      console.error('ProductId is undefined or null');
    }
  }
  removeFromCart(productId: number) {
    this.productService.removeProductFromCart(productId).subscribe(
      response => {
        console.log(response);
        this.getCartDetails(); // Appel de cartDetails après la suppression du produit
      },
      error => {
        console.log(error);
      }
    );
  }

  }


