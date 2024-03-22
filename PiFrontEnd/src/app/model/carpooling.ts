import { FileHandle } from "./file_handle.model";

export interface Carpooling{
    idCarpolling:number;
    pointArrivee:string;
    gouvernorat:string;
    adresse:string;
  numero:number;
  name:string;
  pointSorite:string;
  price:number;
  description:string;
  nbrPlaceDisponible:number;
  DateSortie:Date;
  imageModels:FileHandle[];


  }