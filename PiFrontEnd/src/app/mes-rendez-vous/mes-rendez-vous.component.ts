import { Component, Inject  } from '@angular/core';
import {user} from "../model/User";
import {RendezVous} from "../model/RendezVous";
import {RendezVousService} from "../Service/RendezVousService";
import dayGridPlugin from '@fullcalendar/daygrid';
import { CalendarOptions,EventInput  } from '@fullcalendar/core';
import { createPopper } from '@popperjs/core';

@Component({
  selector: 'app-mes-rendez-vous',
  templateUrl: './mes-rendez-vous.component.html',
  styleUrls: ['./mes-rendez-vous.component.css']
})
export class MesRendezVousComponent {
  constructor(private RendezVousService:RendezVousService){

  }
  RendezVousOwner: RendezVous[] = [];
  RendezVousALL: RendezVous[] = [];
  user = new user();
  calendarOptions!: CalendarOptions;
  events: EventInput[]=[];
  tooltip: any;
  showDescription: boolean = false;
  currentDescription: string = '';
  descriptionTop: number = 0;
  descriptionLeft: number = 0;
  ngOnInit() {
    this.calendarOptions = {
      plugins: [dayGridPlugin],
      initialView: 'dayGridMonth',
      weekends: true,
      eventClick: this.showEventDescription.bind(this),
      eventMouseLeave: this.hideEventDescription.bind(this)
    };
    const currentDate: Date = new Date();
    const currentYear: number = currentDate.getFullYear();
    const currentMonth: number = currentDate.getMonth() ;
    const currentDay: number = currentDate.getDate();
    this.user.id_user=2;
    this.RendezVousService.findbyOwner(this.user.id_user).subscribe((data: any) => {
      this.RendezVousOwner=data;

      if(this.RendezVousOwner&&this.RendezVousOwner.length>0){
        for(let i=0;i<this.RendezVousOwner.length;i++){
          const rendezvousDate: Date = new Date(this.RendezVousOwner[i].date);
          this.RendezVousOwner[i].IsitPassed=false;
          if(rendezvousDate.getFullYear()<currentYear ||rendezvousDate.getMonth()< currentMonth  || (rendezvousDate.getDate()<currentDay &&  rendezvousDate.getMonth()== currentMonth)){
            this.RendezVousOwner[i].IsitPassed=true;
          }
        }
      }
      this.RendezVousService.findbyOwnerOrclient(this.user.id_user).subscribe((dataa: any) => {
        this.RendezVousALL=dataa;
        if (this.RendezVousALL && this.RendezVousALL.length > 0) {
          let i=0;
          this.RendezVousALL.forEach(rendezVous => {
            i++;
            const datee=new Date(rendezVous.date);
            const year: number = datee.getFullYear();
            const mon: number = datee.getMonth()+1 ;
            const day: number = datee.getDate();
            const formattedDate = `${year}-${mon < 10 ? '0' + mon : mon}-${day < 10 ? '0' + day : day}`;
            let text='';
            if(rendezVous.owner.id_user==this.user.id_user){
              text='You have an appointment with '+rendezVous.client.username+' in the '+formattedDate + 'for the Announce'+rendezVous.annonceCollocation.description
            }else{
              text='You have an appointment with '+rendezVous.owner.username+' in the '+formattedDate + 'for the Announce'+rendezVous.annonceCollocation.description
            }
            const event = {
              title: 'Appointment'+i,
              date: formattedDate,
              description: text

            };
            this.events.push(event);
          });
        }

        this.calendarOptions.events = this.events
      });
    });
  }

  onRefuse(id:number){
    this.RendezVousService.Refuse(id).subscribe((data)=>{
this.RendezVousOwner=this.RendezVousOwner.filter(r=> r.id!=id)
    },(error)=>{
console.log(error);
    }
    )
  }
  remove(id:number){
    this.RendezVousService.Refuse(id).subscribe((data)=>{
this.RendezVousOwner=this.RendezVousOwner.filter(r=> r.id!=id)
    },(error)=>{
console.log(error);
    }
    )
  }
  OnAccapete(id:number){
    this.RendezVousService.Accaepte(id).subscribe((data)=>{
      const datee=new Date(data.date);
      const year: number = datee.getFullYear();
      const mon: number = datee.getMonth()+1 ;
      const day: number = datee.getDate();
      const formattedDate = `${year}-${mon < 10 ? '0' + mon : mon}-${day < 10 ? '0' + day : day}`;

      const event = {
        title: 'Appointment'+this.events.length+1,
        date: formattedDate,
        description: data.description

      };
      console.log(this.events)
      this.events.push(event);
      location.reload();
      this.RendezVousOwner=this.RendezVousOwner.filter(r=> r.id!=id)
          },(error)=>{
      console.log(error);
          }
          )
  }
  showEventDescription(info: any) {
    if(info){

      this.currentDescription = info.event._def.extendedProps.description;
      this.showDescription = true;
    }
  }

  hideEventDescription() {
    this.showDescription = false;
    this.currentDescription ='';
  }
}
