import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UploadService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'image-upload';
  uploadImageForm: FormGroup;
  disableSubmitButton = false;
  selectedImage:any;
  serverError: any;
  uploadSuccess: any;
  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private readonly _formBuilder: FormBuilder
    , private readonly _uploadService: UploadService) {
  }

  ngOnInit(): void {
    this.uploadImageForm = this._formBuilder.group({
      description: ['', Validators.required],
      file: ['', Validators.required]
    });
  }

  onChooseFile(event: any): void {
    let file = event.target.files[0];

    if (file.size > 500 * 1024) {
      this.uploadImageForm.get('file').setValue("");
      this.selectedImage = "";
      alert("File size has to be less than 500KB");
    } else {
      this.selectedImage = event.target.files[0];
    }
  }

  onSave() {
    if (this.uploadImageForm.valid) {
      let data = this.uploadImageForm.value;
      let uploadFormData = new FormData();
      let requestData: any ={
        description: data.description,
        fileSize: this.selectedImage.size,
        fileType: this.selectedImage.type
      };

      const blobOverrides = new Blob([JSON.stringify(requestData)], {
        type: 'application/json',
      });

      uploadFormData.append("file", this.selectedImage);
      uploadFormData.append("data", blobOverrides);
      
      this._uploadService.save(uploadFormData).subscribe(res => {
        this.clearForm();
        this.uploadSuccess = true;
        this.serverError = "";
        setTimeout(()=>{
          this.uploadSuccess = false;
     }, 5000);
      }, error => {
        if (error.status == 400) {
          this.serverError = "Please check the input data";
        } else if (error.status == 500) {
          this.serverError = "Something went wrong. Please contact admin.";
        } else {
          this.serverError = "Something went wrong. Please try again.";
        }
      });     
      
    } else {
      this.uploadSuccess = false;
      this.serverError = "failed to upload image";
      this.markFormGroupTouched(this.uploadImageForm);
    }
  }

  clearForm() {
    this.uploadImageForm = this._formBuilder.group({
      description: ['', Validators.required],
      file: ['', Validators.required]
    });
  }

  markFormGroupTouched(formGroup: FormGroup) {
    (<any>Object).values(formGroup.controls).forEach(control => {
        control.markAsTouched();

        if (control.controls) {
            this.markFormGroupTouched(control);
        }
    });
  }
}
