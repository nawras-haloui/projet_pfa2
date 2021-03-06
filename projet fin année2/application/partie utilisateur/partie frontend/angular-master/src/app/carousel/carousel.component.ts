import { Component, OnInit } from '@angular/core';
import { NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {
  
  slideActivate(ngbSlideEvent: NgbSlideEvent) {
    console.log(ngbSlideEvent.source);
    console.log(ngbSlideEvent.paused);
    console.log(NgbSlideEventSource.INDICATOR);
    console.log(NgbSlideEventSource.ARROW_LEFT);
    console.log(NgbSlideEventSource.ARROW_RIGHT);
  }
  constructor(private _router:Router) { }

  ngOnInit(): void {
  }

  searchAnnonce(ecole:string,type:string,capacity:String){
    
   console.log('keywords :',type,ecole,capacity);
   if(ecole =="institution" && type=="Type" ){
    this._router.navigateByUrl('/search/'+"Ensit"+'/'+"All"+"/"+1);
   }
   else if(ecole =="institution" ){
    this._router.navigateByUrl('/search/'+type);

   }
   else if(capacity == "Capacity" ){
    this._router.navigateByUrl('/search/'+ecole+'/'+type+'/'+1);

   }
    else {
      this._router.navigateByUrl('/search/'+ecole+'/'+type+'/'+capacity);

    }
  

  }

}
