import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor( private authService:AuthService,private userService:UserService,private router:Router) { }
  user:UserDataInfo;
  username:any;
  isImg:boolean;
  img:any;
  ngOnInit(): void {
    this.userService.UserInfo().subscribe(data=>{
      this.user = data;
      
      this.username = this.authService.isLogin('username');
      if( this.user.logo ){
        this.isImg = true;
        this.user.logo ='data:image/jpeg;base64,'+this.user.logo;
      }
      if( !this.user.logo ){
        this.isImg = false;
      }
    })
  }
  onLogount(){
    this.authService.Logout();
  }
  HrefToUpdateName(){
    this.router.navigate(['accountSetting']);
  }
}
