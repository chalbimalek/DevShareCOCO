import { Type_Event } from './enumerations/Type_Event';
import { User } from './User';
import { Post } from './Post';
import { Media } from './Media';

export class Event {
  id!: number;
  location!: string;
  start!: string;
  end!: string;
  event!: Type_Event;
  create!: Date;
  users!: User[];
  post!: Post[];
  poster!: Media[];
  topic!:string;
  nbparticipant!:number;
}
