import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventService } from 'src/app/Service/event.service';


@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {

 event:any ;
  events :any ;
  editEventForm!: FormGroup;
  deleteEventForm!: FormGroup;
  constructor(private eventservice:EventService,
    
    private fb: FormBuilder,
    private router: Router){}

    ngOnInit(): void {
    this.getEvents();
   
    }
  



    getEvents() {
      this.eventservice.getevnts().subscribe(
        (data) => {
          this.events = data;
          console.log(this.events);
        },
        (error) => {
          console.log(error);
        }
      );
    }

    getEvent(event: any) {
      this.event = event;
      this.editEventForm= this.fb.group({
        topic: [this.event.topic, Validators.required],
        endDate: [this.event.endDate, Validators.required],
        startDate: [this.event.startDate, Validators.required],
        location_event: [this.event.location_event, Validators.required],
      });
    }

    deleteEvent() {
      this.eventservice.deletevent(this.event.id).subscribe(
        (response: any) => {
          // Reload the users list after successful deletion
          this.getEvents();
        },
        (error) => {
          console.log(error);
        }
      );
  
    }

  
}

