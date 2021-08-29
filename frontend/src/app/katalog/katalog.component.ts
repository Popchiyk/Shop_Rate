import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { Kategory } from '../Services/Classes/Kategory';
import { Shop } from '../Services/Classes/Shop';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-katalog',
  templateUrl: './katalog.component.html',
  styleUrls: ['./katalog.component.css']
})
export class KatalogComponent implements OnInit {
  img:any;
  shop:Shop[];
  ListAllShop:boolean;
  isListKategory:boolean;
  isRating:boolean;
  isResponce:boolean;
  FindKategoryShop:Shop[];
  rartingSort:Array<any>;
  kategory:Kategory[];
  id_kategory:number;
  token:any;
  isSuccesfull:boolean;
  arr:Array<any>;
  arr1:Array<any>;
  constructor(private authService :AuthService,private userService:UserService,private router:ActivatedRoute,private _router:Router) { }

  ngOnInit(): void {
    this.token=this.authService.isLogin("token");
    this.GetAllKategory();
    let first_li = document.querySelectorAll('.content_link_list>ul>li:first-child');
    first_li[0].classList.add("selectedLink");
    let second_li = document.querySelectorAll('.content_link_list>ul>li:nth-child(2)');
    let three_li = document.querySelectorAll('.content_link_list>ul>li:nth-child(3)');
    this.router.params.subscribe(params=>{
    let id=parseInt(params['']);
    let url=params[''];
      if(url=='rating'){
        this.isRating=true;
        this.isResponce=false;
        second_li[0].classList.add("selectedLink");
        first_li[0].classList.remove("selectedLink");
      }

      if(url=='responce'){
        this.isResponce=true;
        this.isRating=false;
        three_li[0].classList.add("selectedLink");
        first_li[0].classList.remove("selectedLink");
      }

      if( typeof id  === "number" && !isNaN(id)){
      this.id_kategory=id;
        this.isListKategory=true;
        this.ListAllShop=false;
        first_li[0].classList.add("selectedLink");
        three_li[0].setAttribute("style","display:none")
        second_li[0].setAttribute("style","display:none");
        this.GetAllFindKategory(this.id_kategory)
      }
      else{
        this.ListAllShop=true;
        this.isListKategory=false;
        if(this.token){
          this.GetAllShopForUser()
        }else{
          this.GetAllShop();
        }
       
      }
    });
  }

  AddtoFavourite(id:number){
    if(this.token){
      this.userService.SetFavourite(id).subscribe(data=>{
        this.isSuccesfull=true;
        window.scrollTo(0,0);
        setTimeout(() => {
          location.reload();
         }, 3000);

      })
    }else{
      this._router.navigate(['login']);
    }
  }

  GetAllFindKategory(id:number){ 
    if(this.token){
      this.userService.FindKategorybyUser(id).subscribe(data=>{
        this.FindKategoryShop=data;
        this.FindKategoryShop.sort(function(c,b){
          if(c.ball<b.ball){
            return -1;
          }
          if(c.ball>b.ball){
            return 1;
          }
          return 0;
          
        })
        this.FindKategoryShop.reverse();
       let a =Array<Shop[]>();
       for(var i=0;i<this.FindKategoryShop.length;i+=7){
        a.push(this.FindKategoryShop.slice(i,i+7))
      }
      this.arr1 = a;
      for(var j=0;j<this.FindKategoryShop.length;j++){
        this.FindKategoryShop[j].logo='data:image/jpeg;base64,'+this.FindKategoryShop[j].logo;
      }
      })
    }
    else{
      this.userService.findByKategory(id).subscribe(data=>{
        this.FindKategoryShop=data;
        this.FindKategoryShop.sort(function(c,b){
          if(c.ball<b.ball){
            return -1;
          }
          if(c.ball>b.ball){
            return 1;
          }
          return 0;
          
        })
        this.FindKategoryShop.reverse();
       let a =Array<Shop[]>();
       for(var i=0;i<this.FindKategoryShop.length;i+=7){
        a.push(this.FindKategoryShop.slice(i,i+7))
      }
      this.arr1 = a;
      for(var j=0;j<this.FindKategoryShop.length;j++){
        this.FindKategoryShop[j].logo='data:image/jpeg;base64,'+this.FindKategoryShop[j].logo;
      }
      })
    }

   
  }

  searchShop(key:string){
    const result=Array<Shop>();
    if(this.isListKategory){
      for(const shops of this.FindKategoryShop){
        if(shops.name_shop.toLowerCase().indexOf(key.toLowerCase()) !== -1){
          result.push(shops)
        }
        this.arr1=Array.of(result);
      }
      if(result.length === 0 || !key){
        this.GetAllFindKategory(this.id_kategory);
      }
    }
    if(this.ListAllShop){
      for(const shops of this.shop){
        if(shops.name_shop.toLowerCase().indexOf(key.toLowerCase()) !== -1){
          result.push(shops);
        }
      }
      this.arr=Array.of(result);
    if(result.length === 0 || !key){
      if(this.token){
        this.GetAllShopForUser();
      }else{
        this.GetAllShop();
      }
     
    }
    } 
  }
  GetAllShop(){
    this.userService.GetAllShop().subscribe(data=>{
      this.shop=data;
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
      if(this.isResponce){
        this.shop.sort(function(c,b){
          if(c.response<b.response){
            return -1;
          }
          if(c.response>b.response){
            return 1;
          }
          return 0;
        })
        this.shop.reverse();
      }
      let a = Array<Shop[]>();
      for(var i=0;i<this.shop.length;i+=7){
        a.push(this.shop.slice(i,i+7))
      }
      this.arr = a;      
      this.rartingSort=this.arr;
     
      for(var j=0;j<this.shop.length;j++){
        this.shop[j].logo='data:image/jpeg;base64,'+this.shop[j].logo;
      }
    })
  }

  GetAllShopForUser(){
    this.userService.GetAllForUser().subscribe(data=>{
      this.shop=data;
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
      if(this.isResponce){
        this.shop.sort(function(c,b){
          if(c.response<b.response){
            return -1;
          }
          if(c.response>b.response){
            return 1;
          }
          return 0;
        })
        this.shop.reverse();
      }
      let a = Array<Shop[]>();
      for(var i=0;i<this.shop.length;i+=7){
        a.push(this.shop.slice(i,i+7))
      }
      this.arr = a;      
      this.rartingSort=this.arr;
     
      for(var j=0;j<this.shop.length;j++){
        this.shop[j].logo='data:image/jpeg;base64,'+this.shop[j].logo;
      }
    })
  }

  GetAllKategory(){
    this.GetAllShop();
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
}
