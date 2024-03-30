import { Component, OnInit } from '@angular/core';
import { Product } from '../../model/product';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../Service/product.service';
import { OrderDetails } from '../../model/OrderDetails';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-buy-product',
  templateUrl: './buy-product.component.html',
  styleUrls: ['./buy-product.component.css']
})
export class BuyProductComponent  implements OnInit {

  isSingleProductCheckout : string = "";
  productDetails : Product[]=[];
  orderDetails: OrderDetails={
    fullName : '',
	  fullAddress: '',
	  contactNumber : '',
	  alternateContactNumber : '',
	  orderProductQuantityList : []
  }
  constructor( private activatedRoute: ActivatedRoute,
    private productService : ProductService,
    private router: Router) { }

    ngOnInit(): void {
      this.productDetails = this.activatedRoute.snapshot.data['productDetails'];
      this.loadStripe();
      // Using the non-null assertion operator (!)
      this.isSingleProductCheckout = this.activatedRoute.snapshot.paramMap.get("isSingleProductCheckout")!;
  
      this.productDetails.forEach(
          x => this.orderDetails.orderProductQuantityList.push(
              {
                  productId: x.idProduct,
                  quantity: 1
              }
          )
      );
      console.log(this.productDetails);
      console.log(this.orderDetails);
  }
  

  public placeOrder(orderForm : NgForm){
    this.productService.placeOrder(this.orderDetails, this.isSingleProductCheckout).subscribe(
      (resp) => {
        console.log(resp);
        orderForm.reset();
        console.log("confirmationnnnnnn");
        Swal.fire('Success!', 'Your order place successfully.  It will be delivered to you with in 4-5 days', 'success');

        this.router.navigate(["/myOrders"])
      },
      (err) => {
        console.log(err);
      }
    );

  }

  getQuantityForProduct(productId:any){
    const filterProduct = this.orderDetails.orderProductQuantityList.filter(
      (productQuantity) => productQuantity.productId === productId
    );
    return filterProduct[0].quantity;

  }

  getCalculatedTotal(productId:any, productDiscountedPrice:any){
    const filterProduct = this.orderDetails.orderProductQuantityList.filter(
      (productQuantity) => productQuantity.productId === productId
    );
    return filterProduct[0].quantity*productDiscountedPrice;

  }

  onQuantityChanged(q:any, productId:any){
    this.orderDetails.orderProductQuantityList.filter(
      (orderProduct) => orderProduct.productId === productId
    )[0].quantity=q;
  }

  getCalculatedGrandTotal(){
    let grandTotal = 0;
    this.orderDetails.orderProductQuantityList.forEach(
      (productQuantity) => {
        const price=this.productDetails.filter(product => product.idProduct === productQuantity.productId)[0].price
        grandTotal+=price*productQuantity.quantity;
      }
    );
    return grandTotal;
  }
  handler:any = null;
  payEnabled: boolean = false;

  pay(amount: any) {    
 
    var handler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_51HxRkiCumzEESdU2Z1FzfCVAJyiVHyHifo0GeCMAyzHPFme6v6ahYeYbQPpD9BvXbAacO2yFQ8ETlKjo4pkHSHSh00qKzqUVK9',
      locale: 'auto',
      token: (token: any) => {  // Utilisez une fonction fléchée ici aussi
        console.log(token)
        alert('Payment Success!!');
        this.payEnabled = true; // Assurez-vous que this fait référence à l'instance correcte de la classe
      }
    });
 
    handler.open({
      name: 'Demo Site',
      description: '2 widgets',
      amount: amount * 100
    });
 
  }
 
  loadStripe() {
     
    if(!window.document.getElementById('stripe-script')) {
      var s = window.document.createElement("script");
      s.id = "stripe-script";
      s.type = "text/javascript";
      s.src = "https://checkout.stripe.com/checkout.js";
      s.onload = () => {
        this.handler = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51HxRkiCumzEESdU2Z1FzfCVAJyiVHyHifo0GeCMAyzHPFme6v6ahYeYbQPpD9BvXbAacO2yFQ8ETlKjo4pkHSHSh00qKzqUVK9',
          locale: 'auto',

          token: (token: any) => {  // Utilisez une fonction fléchée ici aussi
            console.log(token)
            alert('Payment Success!!');
            this.payEnabled = true; // Assurez-vous que this fait référence à l'instance correcte de la classe
          }
        });
      }
       
      window.document.body.appendChild(s);
    }
  }

}
