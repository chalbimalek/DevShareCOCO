import { Component,ViewChild,ElementRef,Inject  } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { AngularFireStorage } from '@angular/fire/compat/storage';
import {AnnonceCollocation} from "../model/AnnonceCollocation";
import {user} from "../model/User";
import { MatDialog,MAT_DIALOG_DATA } from '@angular/material/dialog';
import {Type_annon_Collocation} from "../model/Type_annon_Collocation";
import {Type_logement} from "../model/Type_logement";
import {Observable,finalize} from "rxjs";
import * as L from 'leaflet';
import { HttpClient } from '@angular/common/http';
import {AnnonceCollocationService} from "../Service/AnnonceCollocationService";

@Component({
  selector: 'app-edit-annoance',
  templateUrl: './edit-annoance.component.html',
  styleUrls: ['./edit-annoance.component.css']
})
export class EditAnnoanceComponent {
  constructor(private AnnonceCollocationService: AnnonceCollocationService,private formBuilder: FormBuilder,private storage: AngularFireStorage, private httpClient: HttpClient,@Inject(MAT_DIALOG_DATA) public data: any){}
  AnnonceCollocation=new AnnonceCollocation();
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
    if (this.data) {
      this.newCollocationFormGroup.patchValue({
        id: this.data.Annoance.id_anno_colo || '', 
        addresse: this.data.Annoance.addresse || '',
        ville: this.data.Annoance.ville || '', 
        pays: this.data.Annoance.pays || '',
        status: this.data.Annoance.status || '', 
        nbrChambre: this.data.Annoance.nbrChambre || '',
        meuble: this.data.Annoance.meuble || '', 
        cautionnement: this.data.Annoance.cautionnement || '',
        sexe: this.data.Annoance.sexe || '', 
        type_logement: this.data.Annoance.typeLogement || '',
        type_annon_collocation: this.data.Annoance.typeAnnonCollocation || '', 
        description: this.data.Annoance.description || '',
        montantContrubition: this.data.Annoance.montantContrubition || '', 
        nbrPersonne: this.data.Annoance.nbrPersonne || ''
      });
    }
  }
   ngAfterViewInit(): void {
    this.initMap();
    }
   initializeForm(): void {
    this.newCollocationFormGroup = this.formBuilder.group({
      id: ['', Validators.required],
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
      status: ['', Validators.required],
      montantContrubition: ['',[Validators.required, Validators.pattern('^[0-9]+$')]],
      nbrPersonne: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
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
  OnSubmitEdit(){
    if(this.newCollocationFormGroup.valid){
      this.AnnonceCollocation.id_anno_colo=this.newCollocationFormGroup.get('id')!.value;
     this.AnnonceCollocation.addresse=this.newCollocationFormGroup.get('addresse')!.value;
     this.AnnonceCollocation.ville=this.newCollocationFormGroup.get('ville')!.value;
     this.AnnonceCollocation.pays=this.newCollocationFormGroup.get('pays')!.value;
     this.AnnonceCollocation.nbrChambre=this.newCollocationFormGroup.get('nbrChambre')!.value;
     this.AnnonceCollocation.nbrPersonne=this.newCollocationFormGroup.get('nbrPersonne')!.value;
     this.AnnonceCollocation.meuble=this.newCollocationFormGroup.get('meuble')!.value;
     if(this.newCollocationFormGroup.get('status')!.value=="true")
      this.AnnonceCollocation.status=true;
    else
    this.AnnonceCollocation.status=false;
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
     
     this.AnnonceCollocationService.updateAnnonceCollocation(this.AnnonceCollocation).subscribe(us =>{
      
      if(us){
       
        console.log(us)
        location.reload();
      }
    },(error =>{
        console.log(error)
       
  }))
    }else{
     console.log(this.newCollocationFormGroup.errors)
    }
     
    }
}
