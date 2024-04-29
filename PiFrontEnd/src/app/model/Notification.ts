import { Auser } from "./Auser";
import { Carpooling } from "./carpooling";

export interface notification{

    message:String,
    timestamp:Date,
    userEnvoyer: Auser;
    userDestiner: Auser;
    carpooling:Carpooling,
    acceptee:boolean
    refusee:boolean
}