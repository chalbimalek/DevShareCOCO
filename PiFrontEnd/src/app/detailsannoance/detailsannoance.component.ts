import { Component, ViewChild, ElementRef,TemplateRef } from '@angular/core';
import { ActivatedRoute,Router } from '@angular/router';
import {AnnonceCollocation} from "../model/AnnonceCollocation";
import {user} from "../model/User";
import {RendezVous} from "../model/RendezVous";
import {AnnonceCollocationService} from "../Service/AnnonceCollocationService";
import {RendezVousService} from "../Service/RendezVousService";
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import * as L from 'leaflet';
import { HttpClient } from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatDialog} from '@angular/material/dialog';
@Component({
  selector: 'app-detailsannoance',
  templateUrl: './detailsannoance.component.html',
  styleUrls: ['./detailsannoance.component.css']
})
export class DetailsannoanceComponent {
constructor(private router:ActivatedRoute,private formBuilder: FormBuilder,private dialogRef : MatDialog,private AnnonceCollocationService:AnnonceCollocationService,private RendezVousService:RendezVousService,private http: HttpClient,private sanitizer: DomSanitizer){}
AnnonceCollocation=new AnnonceCollocation();
message: string = '';
@ViewChild('secondDialog', {static: true}) secondDialog!: TemplateRef<any>;
RendezVouss: RendezVous[] = [];
idannace!:number;
RenderVousFormGroup!: FormGroup;
map: any;
baseUrl = 'https://nominatim.openstreetmap.org';
user = new user();
errorr:string='';
openrender:boolean=false;
ngOnInit() {
  this.initializeForm();
  this.user.id_user=2;
  this.router.params.subscribe(params => {
   this.idannace= +params['id'];
 this.AnnonceCollocationService.getOneAnnonceCollocation(this.idannace).subscribe((data: any) => {
  this.AnnonceCollocation=data;
  this.RendezVousService.findbyannoance(this.idannace).subscribe((data: any) => {
this.RendezVouss=data;
console.log(this.RendezVouss)
  });

});
  });
}
initializeForm(): void {
  this.RenderVousFormGroup = this.formBuilder.group({
    date: ['', Validators.required],

  });
}
openpup(){
  this.openrender=true;
}
close(){
  this.openrender=false;
}
onRendezVous(): void {
  if(this.RenderVousFormGroup.get('date')!.value==null || this.RenderVousFormGroup.get('date')!.value==''){
    this.errorr = "You need to pick a date.";
    return;
  }

  else{
    const chosenDate: Date = new Date(this.RenderVousFormGroup.get('date')!.value);
  const currentDate: Date = new Date();
  const currentYear: number = currentDate.getFullYear();
  const currentMonth: number = currentDate.getMonth() ;
  const currentDay: number = currentDate.getDate();
  const chosenYear: number = chosenDate.getFullYear();
  const chosenMonth: number = chosenDate.getMonth() ;
  const chosentDay: number = chosenDate.getDate();
  console.log(currentMonth)
  console.log(currentDay)
  console.log(chosenMonth)
  console.log(chosentDay)
  console.log((chosentDay<currentDay &&  chosenMonth== currentMonth))
  console.log(chosenMonth< currentMonth)
  console.log(chosenYear<currentYear)
  if(chosenYear<currentYear ||chosenMonth< currentMonth  || (chosentDay<currentDay &&  chosenMonth== currentMonth)){
    this.errorr = "Please select a date in the future.";
    return;
  }else{
    if (this.RendezVouss.length > 0) {
      const isDateTaken: boolean = this.RendezVouss.some(rendezVous => {
        const rendezvousDate: Date = new Date(rendezVous.date);
        return rendezvousDate.getFullYear() === chosenDate.getFullYear() && rendezvousDate.getMonth() === chosenDate.getMonth() && rendezvousDate.getDate() === chosenDate.getDate() ;
      });
      if (isDateTaken) {
        this.errorr = "This date has already been taken.";
        return;
      } else{
        let r = new RendezVous();
        r.date = chosenDate;
        this.RendezVousService.addRendezVous(r, this.user.id_user, this.idannace).subscribe(us => {
          if (us) {
            this.openrender = false;
            this.message = 'Appointment was set successfully!';
            this.dialogRef.open(this.secondDialog);
            setTimeout(() => {
              this.dialogRef.closeAll();
            }, 3000);
          }
        }, (error => {
          this.errorr = error;
        }));
      }
    }
    else{
      let r = new RendezVous();
      r.date = chosenDate;
      this.RendezVousService.addRendezVous(r, this.user.id_user, this.idannace).subscribe(us => {
        if (us) {
          this.openrender = false;
          this.message = 'Appointment was set successfully!';
          this.dialogRef.open(this.secondDialog);
          setTimeout(() => {
            this.dialogRef.closeAll();
          }, 3000);
        }
      }, (error => {
        this.errorr = error;
      }));
    }


  }
}
}
ngAfterViewInit(): void {
  this.initMap();
}

initMap(): void {
  this.map = L.map('map').setView([0, 0], 2); // Centrer initialement sur la carte du monde avec un zoom de 2
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(this.map);

  const location = `${this.AnnonceCollocation.addresse}, ${this.AnnonceCollocation.ville}, ${this.AnnonceCollocation.pays}`;
  this.getCoordinates(location);
}

getCoordinates(location: string): void {
  const encodedLocation = encodeURIComponent(location);
  const url = `${this.baseUrl}/search?q=${encodedLocation}&format=json`;

  this.http.get(url).subscribe((data:any) => {
    console.log(data)
    if (data && data.length > 0) {
      const lat = parseFloat(data.lat);
      const lon = parseFloat(data.lon);
      this.addMarker(lat, lon);
      this.map.setView([lat, lon], 13); // Centrer la carte sur les coordonnées obtenues avec un zoom de 13
    }
  });
}

addMarker(latitude: number, longitude: number): void {
  L.marker([latitude, longitude]).addTo(this.map)
    .bindPopup('Your Location')
    .openPopup();
}
}
