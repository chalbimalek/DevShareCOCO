import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getPosts() {
<<<<<<< HEAD
    return this.http.get(`http://localhost:8081/api/post/retrieveallPOST`);
=======
    return this.http.get(`http://localhost:8080/api/post/retrieveallPOST`);
>>>>>>> developer
  }

  addPost(postData: any): Observable<any> {
    return this.http.post(
<<<<<<< HEAD
      'http://localhost:8081/api/post/addPOST',
=======
      'http://localhost:8080/api/post/addPOST',
>>>>>>> developer
      postData
    );
  }


  deletePost(id: any) {
    return this.http.delete(
<<<<<<< HEAD
      'http://localhost:8081/api/post/deletePost/' + id
=======
      'http://localhost:8080/api/post/deletePost/' + id
>>>>>>> developer
    );
  }

  updatePost(id: any, postData: any): Observable<any> {
    return this.http.put(
<<<<<<< HEAD
      `http://localhost:8081/api/post/EditPost/${id}`,
=======
      `http://localhost:8080/api/post/EditPost/${id}`,
>>>>>>> developer
      postData
    );
  }

  getPostById(id: any) {
    return this.http.get(
<<<<<<< HEAD
      `http://localhost:8081/api/post/retrievePost/${id}`
=======
      `http://localhost:8080/api/post/retrievePost/${id}`
>>>>>>> developer
    );
  }


  public addFile(formData:FormData,id:any): Observable<any>{
   
<<<<<<< HEAD
    return this.http.post(`http://localhost:8081/api/post/AddPostFile/${id}`,formData, {  reportProgress :true, observe:'events'});
=======
    return this.http.post(`http://localhost:8080/api/post/AddPostFile/${id}`,formData, {  reportProgress :true, observe:'events'});
>>>>>>> developer
  }
  public retrieveFile(id:any):Observable<Blob>{
    
    const headers = new HttpHeaders().set('Accept', 'application/octet-stream');
<<<<<<< HEAD
    return this.http.get(`http://localhost:8081/api/post/retrieveFile/${id}`,
=======
    return this.http.get(`http://localhost:8080/api/post/retrieveFile/${id}`,
>>>>>>> developer
      { headers : headers,
               responseType: 'blob'
        });
  }

  SearchPosts(word: any) {
    return this.http.get(
<<<<<<< HEAD
      `http://localhost:8081/api/post/SearchPosts/${word}`
=======
      `http://localhost:8080/api/post/SearchPosts/${word}`
>>>>>>> developer
    );
  }

}
