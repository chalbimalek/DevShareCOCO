import { Component } from '@angular/core';
import {AnnonceCollocationService} from "../../Service/AnnonceCollocationService";
import {AnnonceCollocation} from "../../model/AnnonceCollocation";
@Component({
  selector: 'app-annoance-admin',
  templateUrl: './annoance-admin.component.html',
  styleUrls: ['./annoance-admin.component.css']
})
export class AnnoanceAdminComponent {
  constructor(private AnnonceCollocationService: AnnonceCollocationService){}
  AllAnnonceCollocations: AnnonceCollocation[] = [];
  currentPage = 0;
  totalItems = 0; 
  pageSize = 5;
  ngOnInit(): void {
    this.getAllAnnonceCollocations();
  }
  getAllAnnonceCollocations(): void {
   
    this.AnnonceCollocationService.getAnnonceCollocations(this.currentPage, this.pageSize)
      .subscribe((data: any) => { 
        this.AllAnnonceCollocations = data.content; 
        this.totalItems = data.totalPages;
      });
  }
  onPageChange(page: number): void {
    this.currentPage = page;
    this.getAllAnnonceCollocations();
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
}
