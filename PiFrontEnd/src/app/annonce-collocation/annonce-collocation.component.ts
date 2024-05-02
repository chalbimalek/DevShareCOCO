import { Component,ViewChild,ElementRef } from '@angular/core';
import {AnnonceCollocation} from "../model/AnnonceCollocation";
import {user} from "../model/User";
import {AnnonceCollocationService} from "../Service/AnnonceCollocationService";
import { MatDialog } from '@angular/material/dialog';
import { AddCollocationComponent } from '../add-collocation/add-collocation.component';
import { EditAnnoanceComponent } from '../edit-annoance/edit-annoance.component';
@Component({
  selector: 'app-annonce-collocation',
  templateUrl: './annonce-collocation.component.html',
  styleUrls: ['./annonce-collocation.component.css']
})
export class AnnonceCollocationComponent {
  constructor(private AnnonceCollocationService: AnnonceCollocationService,private dialogRef : MatDialog){}
  AllAnnonceCollocations: AnnonceCollocation[] = [];
  AnnonceCollocation=new AnnonceCollocation();
  user = new user();
  currentPage = 0;
  totalItems = 0; 
  pageSize = 6;
  selectedFile!: File;
 selctedcollocation=new AnnonceCollocation();
 select:boolean=false;
 showcontact: boolean = false;
 message: string = '';
  
  openaddPopup(){
    this.dialogRef.open(AddCollocationComponent, {
      panelClass: 'my-custom-dialog-class'
    });
  }
  showContactForm() {
    this.showcontact =!this.showcontact;
}
  ngOnInit(): void {
    this.user.id_user=1;
    this.getAllAnnonceCollocations();
  }
  getAllAnnonceCollocations(): void {
   
    this.AnnonceCollocationService.getAnnonceCollocations(this.currentPage, this.pageSize)
      .subscribe((data: any) => { 
        this.AllAnnonceCollocations = data.content; 
        this.totalItems = data.totalPages;
        for(let i=0;i<this.AllAnnonceCollocations.length;i++){
          this.AllAnnonceCollocations[i].Isityours=false;
          if(this.AllAnnonceCollocations[i].user.id_user==this.user.id_user){
            this.AllAnnonceCollocations[i].Isityours=true;
          }
        }
      });
  }
  onPageChange(page: number): void {
    this.currentPage = page;
    this.getAllAnnonceCollocations();
  }
  ShowSelect(annoance:AnnonceCollocation){
    this.selctedcollocation=annoance;
    this.select=true;
    console.log(this.selctedcollocation)
  }
 CloseSelect(){
  this.selctedcollocation=new AnnonceCollocation();
  this.select=false;
 }
 OnDelete(id:number){
  this.AnnonceCollocationService.Delete(id).subscribe({
    next: data => {
      location.reload();
    },
    error: err => {
      console.error(err);
    }
  });
 }
 OnContact(){
  this.AnnonceCollocationService.SendRequestMeet(this.user.id_user,this.message).subscribe({
    next: data => {
      location.reload();
    },
    error: err => {
      console.error(err);
    }
  });
 }
 search(event: any): void {
  const query = event.target.value; 
  if (!query) {
    this.getAllAnnonceCollocations();
    return;
  }
  this.AllAnnonceCollocations = this.AllAnnonceCollocations.filter(ann =>
    ann.description.toLowerCase().includes(query.toLowerCase()) ||
    ann.user.username.toLowerCase().includes(query.toLowerCase()) ||
    ann.nbrPersonne.toString() == query ||
    ann.nbrChambre.toString() == query ||
    ann.cautionnement.toString() == query ||
    ann.montantContrubition.toString() == query ||
    ann.typeLogement.toLowerCase().includes(query.toLowerCase()) ||
    ann.typeAnnonCollocation.toLowerCase().includes(query.toLowerCase()) ||
    ann.addresse.toLowerCase().includes(query.toLowerCase()) ||
    ann.ville.toLowerCase().includes(query.toLowerCase()) ||
    ann.pays.toLowerCase().includes(query.toLowerCase()) ||
    ann.sexe.toLowerCase().includes(query.toLowerCase()) ||
    ann.meuble.toLowerCase().includes(query.toLowerCase()) 
  );
}
Gotoedit(Annoance:AnnonceCollocation){
  this.select=false;
  this.dialogRef.open(EditAnnoanceComponent,{data:{Annoance},panelClass: 'my-custom-dialog-class'});
}
}
