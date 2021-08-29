import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { until, utils } from 'protractor';
import { AuthService } from '../Services/auth.service';
import { ReviewRequest } from '../Services/Classes/ReviewRequest';
import { Shop } from '../Services/Classes/Shop';
import { UserInfoRequest } from '../Services/Classes/UserRequestInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-writeresponce',
  templateUrl: './writeresponce.component.html',
  styleUrls: ['./writeresponce.component.css']
})
export class WriteresponceComponent implements OnInit {
  isSuccesfull:boolean;
  isLogin:boolean;
  username:string;
  idShop:number;
  shop:Shop;
  reviewRequest:ReviewRequest
  sendReview:FormGroup;
  starValue:number;
  user:UserInfoRequest
  constructor(private formBuilder: FormBuilder,private auhtService:AuthService,private userService:UserService,private router:ActivatedRoute,private _router:Router) { 
    this.sendReview=this.formBuilder.group({
      header:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      text:["",[Validators.required,Validators.minLength(10),Validators.maxLength(414)]],
    })
    this.reviewRequest={
      ball:0,
      data:"",
      header:"",
      text:""
    }
  }
  ngOnInit(): void {
   
    let token = this.auhtService.isLogin("token");
    this.username = this.auhtService.isLogin("username");
    if( token ){
      this.isLogin=true;
    }else{
      this.isLogin=false;
    }
    this.router.params.subscribe(params=>{
      this.idShop=parseInt(params['']);
      this.userService.FindShop(params['']).subscribe(data=>{
        this.shop=data;
        this.shop.logo ='data:image/jpeg;base64,'+this.shop.logo;
      })
    })
   this.userService.UserInfo().subscribe(data=>{
     this.user=data;
     if(this.user.logo){
      this.user.logo = 'data:image/jpeg;base64,'+this.user.logo;
     }else{
       this.user.logo="";
     }
     
   })
  }
  ToRegistrashion(){
    this._router.navigate(['registrashion']);
  }

  ToLogin(){
    this._router.navigate(['login']);
  }

    Star(event){
      this.starValue=event.target.value;
    }

    SendReview(){
      var today = new Date();
      var dateString =
      today.getUTCFullYear() + "-" +
      ("0" + (today.getUTCMonth()+1)).slice(-2) + "-" +
      ("0" + today.getUTCDate()).slice(-2) ;
      this.reviewRequest.header=this.sendReview.get('header').value;
      this.reviewRequest.text=this.sendReview.get('text').value;
      this.reviewRequest.ball=this.starValue;
      this.reviewRequest.data=dateString;
      if(this.reviewRequest.ball !=undefined){
      this.userService.sendReview(this.reviewRequest,this.idShop).subscribe(data=>{
        this.isSuccesfull=true;
        window.scrollTo(0,0);
        setTimeout(() => {
          this._router.navigate(['katalog']);
         }, 3000);
      },
      (erro : HttpErrorResponse)=>{
        this.isSuccesfull = false;
        alert(erro.message)
      }
      )
      }
    }

    ClearAll(){
      location.reload();
    }
}
