import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../Service/product.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Product } from '../../model/product';
import { map } from 'rxjs';
import { DomSanitizer } from '@angular/platform-browser';
import { ImageProcessingService } from 'src/app/image/image-processing.service';

@Component({
  selector: 'app-marketplace',
  templateUrl: './marketplace.component.html',
  styleUrls: ['./marketplace.component.css']
})
export class MarketplaceComponent implements OnInit {
p:number=1;
  searchTerm: string='' ;
  public productDetails: Product[] = [];

  constructor(private router : Router,private productservice:ProductService,private sanitizer:DomSanitizer,private imageProcessingService:ImageProcessingService){}
  ngOnInit(): void {
    this.getAllProduct();
  }
  public getAllProduct(){
    this.productservice.getAllProduct(this.pageNumber).
    pipe(
     map((products: Product[],i) => products.map((product: Product) => this.imageProcessingService.createImages(product)))
  
    ).
    subscribe(
      (resp:Product[])=>{
      console.log(resp);
      this.productDetails=resp;
    },(error:HttpErrorResponse )=>{
      console.log(error);
    }
    );
  }
  get filteredProducts() {
    return this.productDetails.filter(product => {
      // Filtrer les produits en fonction du terme de recherche
      return product.name.toLowerCase().includes(this.searchTerm.toLowerCase());
    });
  }

  goToProduct(id:any){
    this.router.navigate(['/detailback',{id:id}]);
  }

  formData: FormData = new FormData();

 
  product!:Product ;

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formData.append('image', file);
    }
  }
  /////////////////////////
  pageNumber: number = 0;
  showLoadButton = false;


 
  public getAllProducts(){
    this.productservice.getAllProduct(this.pageNumber)
    .pipe(
      map((x: Product[], i) => x.map((product: Product) => this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: Product[]) =>{
        console.log(resp);
        if(resp.length == 8){
          this.showLoadButton = true;
        }else{this.showLoadButton = false}
        resp.forEach(p => this.productDetails.push(p));
        // this.productDetails = resp;
      }, (error: HttpErrorResponse) => {
        console.log(error);
      }

    );
  }
  pages !:Array<number>;
  setpage(i:any,event:any){
     event.preventDefault();
     this.pageNumber=i;
     this.getAllProduct();
  }
 
  updatePriceRange(event: Event) {
    const target = (event.target as HTMLInputElement);
    if (target && target.value) {
        const price = +target.value; // Convertir en nombre
        if (!isNaN(price)) { // Vérifier si la conversion est valide
            this.productDetails = this.productDetails.filter(product =>{     console.log('Prix du produit :', product.price);
            return product.price <= price;} );
        }
    }
}




  
  currentDate: Date = new Date();
// | paginate :{itemsPerPage:5,currentPage:p};
}
