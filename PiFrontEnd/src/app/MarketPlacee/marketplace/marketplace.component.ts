import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../Service/product.service';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../model/product';

@Component({
  selector: 'app-marketplace',
  templateUrl: './marketplace.component.html',
  styleUrls: ['./marketplace.component.css']
})
export class MarketplaceComponent implements OnInit {

  searchTerm: any ;
  public getproduct: Product[] = []; // Initialisation avec un tableau vide
  constructor(
    private httpClient: HttpClient,
    private productService: ProductService,
    private router: Router,
    
  ) { }
  showForm: boolean = false;
  toggleForm() {
    this.showForm = !this.showForm;
  }
  ngOnInit(): void {
    this.productService.getAllProduct().subscribe((data:any)=>{
      console.log(data)
      this.getproduct=data;
    })
  }
  

  goToProduct(id:number){
    this.router.navigate(['detail' , id]);
  }
  formData: FormData = new FormData();

  onSubmit() {
    this.productService.addProduct(this.formData).subscribe(
      () => {
        console.log('Produit ajouté avec succès');
        // Réinitialiser le formulaire ou rediriger vers une autre page
      },
      error => {
        console.error('Erreur lors de l\'ajout du produit:', error);
        // Gérer l'erreur
      }
    );
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formData.append('image', file);
    }
  }

}
