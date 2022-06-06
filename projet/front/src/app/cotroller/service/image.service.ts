import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Image} from "../../model/image";
import {Observable} from "rxjs";
import {AppComponent} from "../../app.component";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private API = environment.baseUrl + 'image';

  private _images: Array<Image>;
  private _selectedImage: Image;
  private _image: Image;
  private _message: string = '';

  constructor(private http: HttpClient) { }

  public save(file: File){
    const filedata = new FormData();
    filedata.append('file', file, file.name);
    this.http.post<Image>(this.API + '/', filedata)
      .subscribe(data => {
        if (data == null) {
          this.message = 'this Image Already exist';
        }else {
          this.message = '';
          this.selectedImage = new Image();
          window.location.reload();
        }
        console.log(data);
      });

  }

  public findAll(): Observable<Array<Image>> {
    return this.http.get<Array<Image>>(this.API + '/');
  }
    public delete(id: number): Observable<any>{
    return this.http.delete(this.API + '/id/' + id );
  }


  get images(): Array<Image> {
    if (this._images == null) {
      this._images = new Array<Image>();
    }
    return this._images;
  }

  set images(value: Array<Image>) {
    this._images = value;
  }


  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }

  get selectedImage(): Image {
    if (this._selectedImage == null) {
      this._selectedImage =new Image();
    }
    return this._selectedImage;
  }

  set selectedImage(value: Image) {
    this._selectedImage = value;
  }

  get image(): Image {
    if (this._image == null) {
      this._image =new Image();
    }
    return this._image;
  }

  set image(value: Image) {
    this._image = value;
  }
}
