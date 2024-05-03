import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { EventService } from 'src/app/Service/event.service';
import { event1 } from 'src/app/model/event1';
import { EventProcessingService } from '../image/event-processing.service';
import { map } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Type_Event } from 'src/app/model/enumerations/Type_Event';

@Component({
  selector: 'app-list-event',
  templateUrl: './list-event.component.html',
  styleUrls: ['./list-event.component.css']
})
export class ListEventComponent {

p:number=1;
  searchTerm: string='' ;
  public productDetails: event1[] = [];

  constructor(private router : Router,private productservice:EventService,private sanitizer:DomSanitizer,private imageProcessingService:EventProcessingService){}

  ngOnInit(): void {
    this.getAllProduct();

  }
  public getAllProduct(){
    this.productservice.getAllProduct().
    pipe(
     map((products: event1[],i) => products.map((product: event1) => this.imageProcessingService.createImages(product)))
  
    ).
    subscribe(
      (resp:event1[])=>{
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
      return product.title.toLowerCase().includes(this.searchTerm.toLowerCase());
    });
  }

  goToProduct(id:any){
    this.router.navigate(['/detailEvent',{id:id}]);

  }

  formData: FormData = new FormData();

 
  product!:event1 ;

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
    this.productservice.getAllProduct()
    .pipe(
      map((x: event1[], i) => x.map((product: event1) => this.imageProcessingService.createImages(product)))
    )
    .subscribe(
      (resp: event1[]) =>{
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


///////////////
categories: { name: Type_Event; iconClass: string; }[] = [
  { name: Type_Event.ADEVERTISSMENT, iconClass: 'lni lni-dinner' },
  { name: Type_Event.SPORT, iconClass: 'lni lni-control-panel' },
  { name: Type_Event.WORKSHOP, iconClass: 'lni lni-bullhorn' }
  // Ajoutez d'autres catégories si nécessaire
];


/*loadProductsByCategory(category: Type_Event): void {
  this.selectedCategory = category;
  this.productservice.getProductsByCategory(category)
    .subscribe(products => {
      this.productDetails = products.map(product => this.imageProcessingService.createImages(product));
    });
}*/

selectedCategory!: Type_Event  | string;

isSelected(category: any): boolean {
  if (typeof this.selectedCategory === 'string' && this.selectedCategory === 'all') {
    return category === 'all'; // Si selectedCategory est 'all', retourne true seulement si category est 'all'
  } else {
    return this.selectedCategory === category.name; // Compare avec category.name seulement si selectedCategory est de type Category
  }
}
showAllProducts(){
  this.selectedCategory='all';
  this.getAllProduct()
}
totalCarpoolings: number = 0;



}

