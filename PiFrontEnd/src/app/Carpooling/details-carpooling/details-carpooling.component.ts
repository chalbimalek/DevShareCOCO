import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarppolingServiceService } from 'src/app/Service/carppoling-service.service';
import { Carpooling } from 'src/app/model/carpooling';

@Component({
  selector: 'app-details-carpooling',
  templateUrl: './details-carpooling.component.html',
  styleUrls: ['./details-carpooling.component.css']
})
export class DetailsCarpoolingComponent implements OnInit {

  selectProductIndex = 0;
  product!: Carpooling;

  constructor(private activatedRoute: ActivatedRoute, private router : Router,
    private productService: CarppolingServiceService) { }

  ngOnInit(): void {

   this.product = this.activatedRoute.snapshot.data['product'];
    
  }

  changeIndex(index:any){
    this.selectProductIndex=index;
  }




  




  currentDate: Date = new Date();

}
  



