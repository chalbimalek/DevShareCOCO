import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../Service/product.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../model/product';
@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
   product !:Product
   id!: number 
   constructor(
     private productService: ProductService,
     private route: ActivatedRoute
   ) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']; // Vérifiez que 'id' est correctement extrait des paramètres de l'URL
      this.productService.getProductById(this.id).subscribe((data) => {
        this.product = data;
        console.log(data);
      });
    };
  }

