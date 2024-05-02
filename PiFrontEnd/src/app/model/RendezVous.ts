import {user} from '../model/User';
import {AnnonceCollocation} from '../model/AnnonceCollocation';
export class RendezVous{
    id!:number;
    date!: Date;
    status!: boolean;
    description!: string;
    owner!: user;
    client!: user;
    annonceCollocation!:AnnonceCollocation;
   IsitPassed!:boolean;
}
