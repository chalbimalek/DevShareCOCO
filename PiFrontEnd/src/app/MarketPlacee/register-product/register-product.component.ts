import { Component , OnInit} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Observable, Subscriber } from 'rxjs';
import { ProductService } from '../../Service/product.service';
import { Product } from '../../model/product';
import Swal from 'sweetalert2';
import { Media } from '../../model/Media';
export interface Fruit {
  name: string;
}
@Component({
  selector: 'app-register-product',
  templateUrl: './register-product.component.html',
  styleUrls: ['./register-product.component.css']
})
export class RegisterProductComponent  implements OnInit {
  productForm!: FormGroup;
  productObj: Product = new Product();
  file = [];
  image:string='';
  public indexImage: object = {};
  formData: FormData = new FormData();
  isLinear=true;
  myForm!: FormGroup;
  ngOnInit(): void {
   
  }
  selectedFile: File | undefined;
  productData: Product = {} as Product;
  product: Product = new Product();
  quantity: number = 0;
  files: File[] = [];
 // define an empty array of Media objects
  
  onFileSelected(event:any): void {
    this.selectedFile = event.target.files[0];

  }
  constructor(
    private productService: ProductService) {
    this.productForm = new FormGroup({
      name: new FormControl("inserer", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      brand: new FormControl("", [Validators.required]),
      
    });
  }
  mediaList: Media[] = [];
  showForm: boolean = true;

  onClickSubmitForm() {

    if (!this.productForm.invalid) {
      console.log(this.productForm.value);
      const formData = new FormData();
      this.productObj.name = this.productForm.value.name;
      this.productObj.brand = this.productForm.value.brand;
      this.productObj.description = this.productForm.value.description;
      
      // this.productService.addProduct(this.productObj, this.file[0]).subscribe(data =>
      this.productService.addProduct(formData).subscribe(data =>
        
        console.log(data)
      )
      Swal.fire('Success!', 'Event added successfully!', 'success');
      
      // To reset the form
     this.productForm.reset();
     this.showForm = false;
    } else {
    }
  }
  myImage!: Observable<any>;
  base64code!: any;
  onChange = ($event: Event) => {
    const target = $event.target as HTMLInputElement;
    const file: File = (target.files as FileList)[0];
    //console.log(file)
    this.convertToBase64(file)
  }
  convertToBase64(file: File) {
    const observable = new Observable((subscriber: Subscriber<any>) => {
      this.readFile(file, subscriber)
    })
    observable.subscribe((d) => {
      // console.log(d)
      this.myImage = d
      this.base64code = d
    })
  }
  readFile(file: File, subscriber: Subscriber<any>) {
    const filereader = new FileReader();
    filereader.readAsDataURL(file)
    filereader.onload = () => {
      subscriber.next(filereader.result)
      subscriber.complete()
    }
    filereader.onerror = () => {
      subscriber.error()
      subscriber.complete()
    }
  
  }
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

  onFileSelectedd(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.formData.append('image', file);
    }
  }
  
 

  
}
 
  