import { Component,ViewChild,ElementRef  } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { AngularFireStorage } from '@angular/fire/compat/storage';
import {AnnonceCollocation} from "../model/AnnonceCollocation";
import {user} from "../model/User";
import {Type_annon_Collocation} from "../model/Type_annon_Collocation";
import {Type_logement} from "../model/Type_logement";
import {Observable,finalize} from "rxjs";
import * as L from 'leaflet';
import { HttpClient } from '@angular/common/http';
import {AnnonceCollocationService} from "../Service/AnnonceCollocationService";
import { Router } from '@angular/router';
@Component({
  selector: 'app-add-collocation',
  templateUrl: './add-collocation.component.html',
  styleUrls: ['./add-collocation.component.css']
})
export class AddCollocationComponent {
  constructor(private AnnonceCollocationService: AnnonceCollocationService,private router: Router,private formBuilder: FormBuilder,private storage: AngularFireStorage, private httpClient: HttpClient){}
  AnnonceCollocation=new AnnonceCollocation();
  user = new user();
  newCollocationFormGroup!: FormGroup;
  selectedFile!: File;
  downloadURL!: Observable<string>;
  fb!:any;
  @ViewChild('map')
   mapContainer!: ElementRef<HTMLElement>;
   map!:any;
   logementTypes: string[] = ['APPARTEMENT', 'MAISON'];
   annon_collocationTypes: string[] = ['ROOM_SHARING', 'FULL_RENT'];
   isLoading: boolean = false;
   ngOnInit(): void {
    this.initializeForm();
    this.user.id_user=2;
  }
   ngAfterViewInit(): void {
    this.initMap();
    }
    initializeForm(): void {
      this.newCollocationFormGroup = this.formBuilder.group({
        addresse: ['', Validators.required],
        ville: ['', Validators.required],
        pays: ['', Validators.required],
        nbrChambre: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
        meuble: ['', Validators.required],
        cautionnement: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
        sexe: ['', Validators.required],
        type_logement: ['', Validators.required],
        type_annon_collocation: ['', Validators.required],
        description: ['', Validators.required],
        montantContrubition: ['',[Validators.required, Validators.pattern('^[0-9]+$')]],
        nbrPersonne: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      });
    }
    onFileSelected(event:any) {
      this.isLoading = true;
      var n = Date.now();
      const file = event.target.files[0];
      const filePath = `RoomsImages/${n}`;
      const fileRef = this.storage.ref(filePath);
      const task = this.storage.upload(`RoomsImages/${n}`, file);
      task
        .snapshotChanges()
        .pipe(
          finalize(() => {
            this.downloadURL = fileRef.getDownloadURL();
            this.downloadURL.subscribe(url => {
              if (url) {
                this.fb = url;
              }
              console.log(this.fb);
              this.isLoading = false;
            });
          })
        )
        .subscribe(url => {
          if (url) {

          }
        });
    }
   initMap(): void {
    this.map = L.map('map', {
      center: [7.1881, 21.0938],
      zoom: 3
    });
    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);
     // Add click event listener to the map
     this.map.on('click', (event:any) => {
      const { lat, lng } = event.latlng;
      this.getAddressFromCoordinates(lat, lng);
    });
  }
  getAddressFromCoordinates(latitude: number, longitude: number) {
    const url = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${latitude}&lon=${longitude}`;

    this.httpClient.get(url).subscribe((response: any) => {
      const address = response.display_name;
      const city = response.address.city || response.address.village || response.address.town || response.address.hamlet || '';
      const country = response.address.country;

      this.newCollocationFormGroup.controls['addresse'].setValue(address);
      this.newCollocationFormGroup.controls['ville'].setValue(city);
      this.newCollocationFormGroup.controls['pays'].setValue(country);
    }, error => {
      console.error('Error fetching reverse geocoding data:', error);
    });
  }
  OnSubmitAdd(){
   if(this.newCollocationFormGroup.valid){
    this.AnnonceCollocation.addresse=this.newCollocationFormGroup.get('addresse')!.value;
    this.AnnonceCollocation.ville=this.newCollocationFormGroup.get('ville')!.value;
    this.AnnonceCollocation.pays=this.newCollocationFormGroup.get('pays')!.value;
    this.AnnonceCollocation.nbrChambre=this.newCollocationFormGroup.get('nbrChambre')!.value;
    this.AnnonceCollocation.nbrPersonne=this.newCollocationFormGroup.get('nbrPersonne')!.value;
    this.AnnonceCollocation.meuble=this.newCollocationFormGroup.get('meuble')!.value;
    this.AnnonceCollocation.photos=this.fb;
    this.AnnonceCollocation.cautionnement=this.newCollocationFormGroup.get('cautionnement')!.value;
    this.AnnonceCollocation.sexe=this.newCollocationFormGroup.get('sexe')!.value;
    if(this.newCollocationFormGroup.get('type_logement')!.value=="APPARTEMENT")
    this.AnnonceCollocation.typeLogement=Type_logement.APPARTEMENT;
    if(this.newCollocationFormGroup.get('type_logement')!.value=="MAISON")
     this.AnnonceCollocation.typeLogement=Type_logement.MAISON;
    if(this.newCollocationFormGroup.get('type_annon_collocation')!.value=="FULL_RENT")
     this.AnnonceCollocation.typeAnnonCollocation=Type_annon_Collocation.FULL_RENT;
    if(this.newCollocationFormGroup.get('type_annon_collocation')!.value=="ROOM_SHARING")
     this.AnnonceCollocation.typeAnnonCollocation=Type_annon_Collocation.ROOM_SHARING;
    this.AnnonceCollocation.description=this.newCollocationFormGroup.get('description')!.value;
    this.AnnonceCollocation.montantContrubition=this.newCollocationFormGroup.get('montantContrubition')!.value;
    this.AnnonceCollocation.user=this.user;
    this.isLoading=true;
    this.AnnonceCollocationService.addAnnonceCollocation(this.AnnonceCollocation).subscribe(us =>{

     if(us){
      this.isLoading=false;
      this.router.navigate(['/AnnonceCollocation']);
     }
   },(error =>{
       console.log(error)
       this.isLoading=false;
 }))
   }else{
    console.log(this.newCollocationFormGroup.errors)
   }

   }
   onUploadClick() {
    document.getElementById('fileimage')?.click();
  }
}
