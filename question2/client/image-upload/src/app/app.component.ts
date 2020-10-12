import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SearchService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'image-search';
  imageSearchForm: FormGroup;
  disableSubmitButton = false;
  selectedImage:any;
  serverError: any;
  uploadSuccess: any;
  results: any = [];
  page: number = 0;
  throttle = 300;
  scrollDistance = 1;
  scrollUpDistance = 2;
  array = [];
  sum = 20;
  hasNext = true;

  resultColumns = [
    { prop: 'id', name: 'Id', flexSize: 1 },
    { prop: 'description', name: 'Description', flexSize: 3 },
    { prop: 'imageUrl', name: 'Campaign', flexSize: 3 },
    { prop: 'fileType', name: 'File Type', flexSize: 3 },
    { prop: 'fileSize', name: 'File Size', flexSize: 2 },
  ];

  constructor(private readonly _formBuilder: FormBuilder
    , private readonly _seachService: SearchService) {
  }

  ngOnInit(): void {
    this.imageSearchForm = this._formBuilder.group({
      description: [''],
      imageType: [''],
      minSize: ['', Validators.min(0)],
      maxSize: ['', Validators.min(0)],
      pageSize: [20]
    });

    this.onSearch();
  }

  onValueChange() {
    if (this.imageSearchForm.value.maxSize) {
      this.imageSearchForm.get('minSize').setValidators([Validators.max(this.imageSearchForm.value.maxSize)]);
    } else {
      this.imageSearchForm.get('minSize').clearValidators();
      this.imageSearchForm.get('minSize').setValidators([Validators.min(0)]);
    }

    if (this.imageSearchForm.value.minSize) {
      this.imageSearchForm.get('maxSize').setValidators([Validators.min(this.imageSearchForm.value.minSize)]);
    } else {
      this.imageSearchForm.get('maxSize').clearValidators();
      this.imageSearchForm.get('maxSize').setValidators([Validators.min(0)]);
    }
  }

  onSearch() {
    if (this.imageSearchForm.valid) {
      this.page = 0;
      let data = this.imageSearchForm.value;
      data.page = this.page;
      this.hasNext = true;
      this._seachService.search(data).subscribe(res => {
        this.results = [];
        if (res != null) {
          this.results = res;
          this.serverError = "";

          this.hasNext = res.length === 20;
        } else {
          this.hasNext = false;
        }
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
      this.markFormGroupTouched(this.imageSearchForm);
    }
  }
  
  addItems(startIndex, endIndex, _method) {
    for (let i = 0; i < this.sum; ++i) {
      //this.array[_method]([i, ' ', this.generateWord()].join(''));
    }
  }
  
  appendItems(startIndex, endIndex) {
    this.addItems(startIndex, endIndex, 'push');
  }
  
  prependItems(startIndex, endIndex) {
    this.addItems(startIndex, endIndex, 'unshift');
  }

  onScrollDown (ev) {
    console.log('scrolled down!!', ev);

    this.getNext();
  }

  getNext() {
    if (this.imageSearchForm.valid && this.hasNext) {
      this.page += 1;
      let data = this.imageSearchForm.value;
      data.page = this.page;
      this._seachService.search(data).subscribe(res => {
        if (res != null) {
          this.results = this.results.concat(res);
          this.hasNext = res.length === 20;
        } else {
          this.hasNext = false;
        }

        this.serverError = "";
      }, error => {
        this.hasNext = false;
        if (error.status == 400) {
          this.serverError = "Please check the input data";
        } else if (error.status == 500) {
          this.serverError = "Something went wrong. Please contact admin.";
        } else {
          this.serverError = "Something went wrong. Please try again.";
        }
      }); 
    }
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
