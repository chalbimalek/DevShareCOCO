import { Media } from "./Media";
import { Category } from "./enumerations/Category";

export class Product{
  id!:number;
  reference!:string;
  name!:string;
  quantity!:number;
  price!:number;
  description!:string;
  discount!:number;
  PriceAfterDiscount!:number;
  brand!:string;
  category!:Category;
  image!:Media;
  medias!:Media[];
  status!:string;
  validated!: boolean;
  }