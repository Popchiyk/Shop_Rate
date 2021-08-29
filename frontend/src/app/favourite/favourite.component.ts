import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { Shop } from '../Services/Classes/Shop';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  constructor(private authService :AuthService,private userService:UserService,private router:Router) { }
  username:any;
  user:UserDataInfo;
  img:any;
  shop:Shop[];
  arr:Array<any>;
  isImg:boolean;
  ngOnInit(): void {
    this.username = this.authService.isLogin('username');
    if(this.username){
     this.UserInfo();
    }
      this.GetAllShop()
  }

  GetAllShop(){
    this.userService.Favourite(this.username).subscribe(data=>{
      this.shop=data;
      console.log(this.shop)
      let a = Array<Shop[]>();
      for(var i=0;i<this.shop.length;i+=8){
        a.push(this.shop.slice(i,i+8))
      }
      this.arr = a;
      for(var j=0;j<this.shop.length;j++){
        this.shop[j].logo='data:image/jpeg;base64,'+this.shop[j].logo;
      }
    })
  }

  DeleteFavourite(id:number){
    this.userService.DeleteFavourite(id).subscribe(data=>{
      location.reload();
    })
  }
  UserInfo(){
    this.userService.UserInfo().subscribe(data=>{

      this.user = data;
      this.img ='data:image/jpeg;base64,'+this.user.logo;
  
      if( this.user.logo ){
        this.isImg = true;
      }
      if( !this.user.logo ){
        this.isImg = false;
      }
   });
  }

  HrefToProfile(){
    this.router.navigate(['accountSetting']);
  }
}
