import { Component, OnInit } from '@angular/core';
import { AuthService } from '../Services/auth.service';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username:string;
  img:any;
  user:UserDataInfo;
  isImg:boolean;
  isLogin:boolean;
  constructor( private authService:AuthService,private userService:UserService) { }
  ngOnInit(): void {
    this.username = this.authService.isLogin('username');
    if(this.username){
      this.isLogin=true;
      this.userService.UserInfo().subscribe(data=>{
        this.user = data;
        this.img ='data:image/jpeg;base64,'+this.user.logo; 
        if( this.user.logo ){
          this.isImg = true;
        }
        if( !this.user.logo ){
          this.isImg = false;
        }
      })   
    }
    
  }
  logOut(){
    this.authService.Logout()
  }
}
