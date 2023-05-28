import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { Kategory } from '../Services/Classes/Kategory';
import { Shop } from '../Services/Classes/Shop';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  isLogin:boolean;
  constructor(private authService :AuthService,private userService:UserService,private _router:Router,private router:ActivatedRoute) { }
  user:UserDataInfo;
  kategory:Kategory[];
  result:any;
  result1:any;
  username:any;
  shop:Shop[];
  b:Array<any>;
  arr:Array<any>;
  selectedfile:File;
  isRating:boolean;
  isImg:boolean;
  isAdmin:boolean;
  img:any;
  ngOnInit(): void {
        this.username = this.authService.isLogin('username');
   
    if(this.username){
     this.UserInfo();
     this.GetAllForUser();
     
    }else{
      this.userService.GetAllShop().subscribe(data=>{
        this.shop=data;
        let first_li = document.querySelectorAll(".content_link>ul>li:first-child");
        let second_li = document.querySelectorAll(".content_link>ul>li:last-child");
        this.router.params.subscribe(params=>{
          if(params['name']=='rating'){
            this.isRating=true;
            first_li[0].classList.remove("selectedLink");
            second_li[0].classList.add("selectedLink");
          }
          else{
            this.isRating=false;
          }
        })
        if(this.isRating){
          this.shop.sort(function(c,b){
            if(c.ball<b.ball){
              return -1;
            }
            if(c.ball>b.ball){
              return 1;
            }
            return 0;
          })
          this.shop.reverse();
        }
       
        let a = Array<Shop[]>();
        for(var i=0;i<this.shop.length;i+=4){
          a.push(this.shop.slice(i,i+4))
        }
        this.arr = a;
        for(var j=0;j<this.shop.length;j++){
          this.shop[j].logo='data:image/jpeg;base64,'+this.shop[j].logo;
        }
      })
    }
   
      this.GetAllKategory()
      
}
  UserInfo(){
    this.userService.UserInfo().subscribe(data=>{
      this.user = data;
     
      if( this.user.logo ){
        this.isImg = true;
        this.user.logo ='data:image/jpeg;base64,'+this.user.logo;
      }
      if( !this.user.logo ){
        this.isImg = false;
      }
      if(this.username != null){
        this.isLogin = true;
        this.isAdmin = false;
      }
      if(this.username==='Admin'){
        this.isAdmin = true;
      }else{
        this.isAdmin = false;
      }
   });
  }

  AddFavourite(id:number){
    if(this.username){
      this.userService.SetFavourite(id).subscribe(data=>{
       location.reload();
      })
    }else{
      this._router.navigate(['login']);
    }
  }
  GetAllKategory(){
    this.userService.GetAllKategory().subscribe(data=>{
      this.kategory=data;
      this.kategory.sort(function(a,b){
        if(a.name_kategory<b.name_kategory){
          return -1;
        }
        if(a.name_kategory>b.name_kategory){
          return 1;
        }
        return 0;
      })
      this.kategory.reverse();
    })
  }
  
  GetAllForUser(){
    this.userService.GetAllForUser().subscribe(data=>{
      this.shop=data;
      let first_li = document.querySelectorAll(".content_link>ul>li:first-child");
        let second_li = document.querySelectorAll(".content_link>ul>li:last-child");
        this.router.params.subscribe(params=>{
          if(params['name']=='rating'){
            this.isRating=true;
            first_li[0].classList.remove("selectedLink");
            second_li[0].classList.add("selectedLink");
          }
          else{
            this.isRating=false;
          }
        })
        if(this.isRating){
          this.shop.sort(function(c,b){
            if(c.ball<b.ball){
              return -1;
            }
            if(c.ball>b.ball){
              return 1;
            }
            return 0;
          })
          this.shop.reverse();
        }
      let a = Array<Shop[]>();
      for(var i=0;i<this.shop.length;i+=4){
        a.push(this.shop.slice(i,i+4))
      }
      this.arr = a;
      for(var j=0;j<this.shop.length;j++){
        this.shop[j].logo='data:image/jpeg;base64,'+this.shop[j].logo;
      }
    })
  }

  LogOut(){
    this.authService.Logout()
  }

  HrefToKatalog(){
    this._router.navigate(['katalog'])
  }
}
