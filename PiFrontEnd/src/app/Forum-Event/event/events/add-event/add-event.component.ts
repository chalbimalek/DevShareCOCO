import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventService } from 'src/app/Service/event.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {
  addEventForm!: FormGroup ;
  events: any;

  constructor(private router: Router,
    private fb: FormBuilder,private eventservice:EventService){}


  ngOnInit(): void {
    this.addEventForm= this.fb.group({
      topic: ['', Validators.required],
      endDate: ['', Validators.required],
      startDate: ['', Validators.required],
      location_event: ['', Validators.required],
    });
  }

  addEvent() {
    const eventData = this.addEventForm.value;
    console.log('value', eventData);

    this.eventservice.addevent(eventData).subscribe(
      (response) => {
        console.log('Response:', response);
        this.navigateToEventsList();
      },
      (error) => {
        console.error(error);
      }
    );
  }
  navigateToEventsList() {
    this.router.navigate(['/events']);
  }

 
}
