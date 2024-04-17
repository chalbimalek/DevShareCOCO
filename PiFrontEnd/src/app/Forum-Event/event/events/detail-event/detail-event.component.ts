import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/Service/event.service';

@Component({
  selector: 'app-detail-event',
  templateUrl: './detail-event.component.html',
  styleUrls: ['./detail-event.component.css']
})
export class DetailEventComponent implements OnInit {
  event: any ;
  events :any ;
  idEvent : any ;
  
  constructor(private route: ActivatedRoute,
    private eventservice:EventService
  ){}


  ngOnInit(): void {
    this.idEvent = this.route.snapshot.params['id'];
    console.log(this.idEvent);
    this.getEvent(this.idEvent);
  }


  getEvent(id: any) {
    this.eventservice.getEventById(id).subscribe((res: any) => {
      this.event = res;
      console.log(res);
      
      const startDate = new Date(this.event.startDate);
    const endDate = new Date(this.event.endDate);

    // Formater les dates au format "yyyy-MM-dd"
    const startDateFormatted = startDate.toISOString().split('T')[0];
    const endDateFormatted = endDate.toISOString().split('T')[0];

   
  
    });

}


}


