import {user} from '../model/User';
import {Type_logement} from '../model/Type_logement';
import {Type_annon_Collocation} from '../model/Type_annon_Collocation';
export class AnnonceCollocation{
    id_anno_colo!:number;
    addresse!: string;
    ville!: string;
    pays!: string;
    status!: boolean;
    date_disponiblite!: Date;
    nbrChambre!:number;
    meuble!: string;
    photos!: string;
    cautionnement!:number;
    sexe!: string;
    typeLogement!: Type_logement;
    typeAnnonCollocation!: Type_annon_Collocation;
    description!: string;
    montantContrubition!:number;
    nbrPersonne!:number;
    url!:string;
    user!:user;
    Isityours!: boolean;
}
