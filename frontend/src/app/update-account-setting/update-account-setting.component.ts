import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDataInfo } from '../Services/Classes/UserInfo';
import { UserInfoRequest } from '../Services/Classes/UserRequestInfo';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-update-account-setting',
  templateUrl: './update-account-setting.component.html',
  styleUrls: ['./update-account-setting.component.css']
})
export class UpdateAccountSettingComponent implements OnInit {
  isSuccesfull:boolean;
  selectedfile:File;
  isImg:boolean;
  img:any;
  updateForm:FormGroup
  User:UserDataInfo;
  userRequest:UserInfoRequest
  constructor(private _router:Router,private userService:UserService,private formGroup:FormBuilder) {
      this.updateForm = this.formGroup.group({
        surname:['',[Validators.required]],
        name:['',[Validators.required]],
        lastname:['',[Validators.required]],
        phone:['',[Validators.required]],
      })
      this.userRequest={
        lastname:'',
        name:'',
        phone:'',
        surname:'',
        logo:''
      }
   }
  ngOnInit(): void {
    this.userService.UserInfo().subscribe(data=>{
      this.User = data;
      this.img ='data:image/jpeg;base64,'+this.User.logo;
      if( this.User.logo ){
        this.isImg = true;
      }
      if( !this.User.logo ){
        this.isImg = false;
      }
    })
    

}
  onFileSelected(event){
    this.selectedfile = event.target.files[0];
  }
  onLoadImage(){
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile',this.selectedfile,this.selectedfile.name)
    this.userService.onLoadImage(uploadImageData).subscribe(data=>{
      location.reload()
    })
  }
  onDeleteImage(){
    this.userService.deleteImg().subscribe(data=>{
      location.reload()
    });
  }
  Update(){
    this.userRequest.surname = this.updateForm.get('surname').value;
    this.userRequest.name = this.updateForm.get('name').value;
    this.userRequest.lastname = this.updateForm.get('lastname').value;
    this.userRequest.phone = this.updateForm.get('phone').value;
    this.userRequest.logo = this.User.logo;
    this.userService.UpdateData(this.userRequest).subscribe(data=>{
      this.isSuccesfull=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this._router.navigate(['profile']);
       }, 3000);
    })
  }
}
