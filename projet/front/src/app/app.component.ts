import {Component, OnInit} from '@angular/core';
import {ImageService} from "./cotroller/service/image.service";
import {DomSanitizer} from "@angular/platform-browser";
import {Image} from "./model/image";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  constructor(private imageService: ImageService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.findAll();
  }

  title: 'ana';
  file: File;


  onImageUpload(event) {
      this.file = event.target.files[0];
      if(this.file.name == null){
        this.message = 'Field Should not be empty!';
      }else {
        this.message = '';
      }
  }

  findAll(){
    this.imageService.findAll().subscribe(data=>{
      data.forEach(e=>{
        let objectURL = 'data:image/jpeg;base64,' + e.picByte;
        e.picByte = this.sanitizer.bypassSecurityTrustUrl(objectURL);
      })
      this.images = data;
    })
  }


  save() {
    if(this.file == null){
      this.message = 'Select a file Before Saving !';
    }
      this.imageService.save(this.file);
  }

  delete(image: Image) {
    let conf = window.confirm('are you sure to delete selecting Image ?');
    if(conf) {
      this.imageService.delete(image.id).subscribe(
        data => {
          console.log(data);
          window.location.reload();
        }
      );
    }
    this.findAll();
  }

  get image(): Image {
    return  this.imageService.selectedImage;
  }

  set image(value: Image){
    this.imageService.selectedImage = value;
  }

  get images(): Array<Image>{
    return this.imageService.images;
  }

  set images(value: Array<Image>){
    this.imageService.images = value;
  }

  get message(): string {
    return this.imageService.message;
  }

  set message(value: string){
    this.imageService.message = value;
  }

}
