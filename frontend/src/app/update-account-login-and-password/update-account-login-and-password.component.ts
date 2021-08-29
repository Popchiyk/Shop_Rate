import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { UpdateLoginAndPassword } from '../Services/Classes/UpdateLoginAndPassword';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-update-account-login-and-password',
  templateUrl: './update-account-login-and-password.component.html',
  styleUrls: ['./update-account-login-and-password.component.css']
})
export class UpdateAccountLoginAndPasswordComponent implements OnInit {
  isUpdateSuccesful:boolean;
  user:UserDataInfo
  updatedate:UpdateLoginAndPassword
  isLogo:boolean;
  isFail:boolean;
  updateForm:FormGroup;
  username:string;
  constructor(private userService:UserService,private formBuilder:FormBuilder,private router:Router,private authService:AuthService) {
    this.updateForm = this.formBuilder.group({
      login:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      oldpass:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      newpass:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
    })
    this.updatedate={
      login:'',
      oldpassword:'',
      password:''
    }
   }

  ngOnInit(): void {
    this.userService.UserInfo().subscribe(data=>{
      this.user=data;
     this.username = this.authService.isLogin('username');
      if( this.user.logo ){
        this.isLogo=true;
      }
      if( !this.user.logo ){
        this.isLogo=false;
      }
      this.user.logo ='data:image/jpeg;base64,'+this.user.logo;
      
    })
    
  }
  UpdateForm(){
    if(this.updateForm.get('login').value){
      this.updatedate.login = this.updateForm.get('login').value ;
    }else{
      this.updatedate.login = this.username;
    }
    this.updatedate.oldpassword = this.updateForm.get('oldpass').value;
    this.updatedate.password = this.updateForm.get('newpass').value;
  
    this.userService.UpdateLoginAndPassword(this.updatedate).subscribe(data=>{
      this.isUpdateSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this.authService.Logout();
        location.reload();
       }, 3000);
    },(err:HttpErrorResponse)=>{
      this.isFail=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        location.reload();
        window.scrollTo(0,0);
       }, 3000);
    })
  }

}
