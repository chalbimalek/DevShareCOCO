
import { User } from './User';
import { Post } from './Post';

export class Comment {
  id!: number;
  date!: string;
  text!: string;
  react!: string;
  data!: ArrayBuffer;
  nameFile!: string;
  user!: User;
  post!: Post;
}

