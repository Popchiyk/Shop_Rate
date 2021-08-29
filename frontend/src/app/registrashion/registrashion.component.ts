import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { RegistrashionRequest } from '../Services/Classes/RegistrashionRequest';

@Component({
  selector: 'app-registrashion',
  templateUrl: './registrashion.component.html',
  styleUrls: ['./registrashion.component.css']
})
export class RegistrashionComponent implements OnInit {
  registerForm:FormGroup;
  isFailRegistrashion:boolean;
  registrashionRequest:RegistrashionRequest
  isSuccesfullRegistrashio:boolean;
  constructor(private formBuilder: FormBuilder,private authService:AuthService,private _router:Router) { 
    this.registerForm = this.formBuilder.group({
      name:["",[Validators.required,Validators.minLength(3),Validators.maxLength(10)]],
      username:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      surname:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      lastname:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      phone:["+380",[Validators.required,Validators.maxLength(13)]],
      email:["",[Validators.required,Validators.email]],
      password:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]],
      configpassword:["",[Validators.required,Validators.minLength(5),Validators.maxLength(15)]]
    });
    this.registrashionRequest={
    name:'',
    username:'',
    surname:'',
    lastname:'',
    phone:'',
    email:'',
    password:''
    }
  }
    
  
 
  ngOnInit(): void {
  }
  OnSubmit(){
    if(this.registerForm.invalid){
      this.isFailRegistrashion = true;
      window.scrollTo(0,0); 
      setTimeout(() => {
      this.isFailRegistrashion = false;
    }, 3000);
    }
    this.registrashionRequest.name = this.registerForm.get('name').value;
    this.registrashionRequest.username = this.registerForm.get('username').value;
    this.registrashionRequest.surname = this.registerForm.get('surname').value;
    this.registrashionRequest.lastname = this.registerForm.get('lastname').value;
    this.registrashionRequest.phone = this.registerForm.get('phone').value;
    this.registrashionRequest.email = this.registerForm.get('email').value;
    this.registrashionRequest.password = this.registerForm.get('password').value;
    if(this.registerForm.valid && this.registerForm.get('configpassword').value == this.registrashionRequest.password){
    this.authService.registrashion(this.registrashionRequest).subscribe(data=>{
      this.isSuccesfullRegistrashio = true;
      window.scrollTo(0,0);
      setTimeout(() => {
       this._router.navigate(['login']);
      }, 3000);
     
    },
    (erro : HttpErrorResponse)=>{
      this.isSuccesfullRegistrashio = false;
     
    }
    )
  }
  }
}
