import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { LoginRequest } from '../Services/Classes/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  FailToLogin:boolean;
  loginForm:FormGroup;
  loginrequest:LoginRequest
  constructor(private formBuilder: FormBuilder,private authService:AuthService,private _router:Router) {
    this.loginForm = this.formBuilder.group({
      username:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      password:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
    });
    this.loginrequest={
      username:'',
      password:''
      }
   }
 
  
  ngOnInit(): void {
  }
  OnLogin(){
    this.loginrequest.username = this.loginForm.get('username').value;
    this.loginrequest.password = this.loginForm.get('password').value;
    this.authService.login(this.loginrequest).subscribe(data=>{
      if(data){
        this.FailToLogin=false;
        this._router.navigate(['profile'])
      }
    },
    (err:HttpErrorResponse)=>{
         this.FailToLogin=true;
        
        setTimeout(() => this.FailToLogin=false, 1500)
       }
       )
  }
}
