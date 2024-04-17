
import { Event } from './Event';
import { Comment } from './Comment';

export class Post {
  id!: number;
  topic!: string;
  contenu!: string;
  date!: string;
  nameFile!: string;
  data!: ArrayBuffer;
  event!: Event;
  comment!: Comment[];
}


