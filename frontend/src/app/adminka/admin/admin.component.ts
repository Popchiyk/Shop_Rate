import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/Services/admin.service';
import { Kategory } from 'src/app/Services/Classes/Kategory';
import { Review } from 'src/app/Services/Classes/Review';
import { Shop } from 'src/app/Services/Classes/Shop';
import { Userfullinfo } from 'src/app/Services/Classes/UserFullInfo';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
isTableReview:boolean;
isTableUsers:boolean;
isSuccesful:boolean;
isFail:boolean;
isTableInfoShop:boolean;
isTableKategory:boolean;
shop:Shop[]
users:Userfullinfo[];
kategory:Kategory;
review:Review;
  constructor(private router:ActivatedRoute,private adminservice:AdminService) { }

  ngOnInit(): void {
    this.router.params.subscribe(params=>{
      if(params['name']=='review'){
        this.isTableReview=true;
        this.isTableUsers=false;
        this.isTableInfoShop=false;
        this.isTableKategory=false;
        this.GetAllReview();
      }
      if(params['name']=='users'){
        this.isTableUsers=true;
        this.isTableReview=false;
        this.isTableInfoShop=false;
        this.isTableKategory=false;
        this.GetAllUsers();
      }
      if(params['name']=='infoshop'){
        this.isTableInfoShop=true;
        this.isTableReview=false;
        this.isTableUsers=false;
        this.isTableKategory=false;
        this.GetAllShop();
      }
      if(params['name']=='kategory'){
        this.isTableInfoShop=false;
        this.isTableReview=false;
        this.isTableUsers=false;
        this.isTableKategory=true;
        this.GetAllKategory();
      }
    })
  }
  
  GetAllShop(){
    this.adminservice.GetAllShop().subscribe(data=>{
      this.shop=data;
      for(var i=0;i<this.shop.length;i++){
        this.shop[i].logo = 'data:image/jpeg;base64,'+this.shop[i].logo;
      }
    })
  }

  GetAllUsers(){
    this.adminservice.GetAllUser().subscribe(data=>{
      this.users=data;
      for(var i=0;i<this.users.length;i++){
        this.users[i].logo = 'data:image/jpeg;base64,'+this.users[i].logo;
      }
    })
  }

  GetAllKategory(){
    this.adminservice.GetAllKategory().subscribe(data=>{
      this.kategory=data;
    })
  }

  GetAllReview(){
    this.adminservice.GetAllReview().subscribe(data=>{
      this.review=data;
    })
  }

  DeleteShop(id:number){
    this.adminservice.DeleteShop(id).subscribe(data=>{
      this.isSuccesful=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    },(err:HttpErrorResponse)=>{
      this.isFail=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    })
  }

  DeleteUser(id:number){
    this.adminservice.DeleteUser(id).subscribe(data=>{
      this.isSuccesful=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    },(err:HttpErrorResponse)=>{
      this.isFail=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    })
  }

  DeleteKategory(id:number){
    this.adminservice.DeleteKategory(id).subscribe(data=>{
      this.isSuccesful=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    },(err:HttpErrorResponse)=>{
      this.isFail=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    }
    )
  }

  DeleteReview(id:number){
    this.adminservice.DeleteReview(id).subscribe(data=>{
      this.isSuccesful=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    },(err:HttpErrorResponse)=>{
      this.isFail=true;
      setTimeout(() => {
        location.reload();
       }, 3000);
    })
  }
}
