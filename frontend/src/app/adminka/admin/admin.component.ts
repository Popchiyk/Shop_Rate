import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/Services/admin.service';
import { ChartDTO } from 'src/app/Services/Classes/ChartDTO';
import { Kategory } from 'src/app/Services/Classes/Kategory';
import { Review } from 'src/app/Services/Classes/Review';
import { Shop } from 'src/app/Services/Classes/Shop';
import { StatsDTO } from 'src/app/Services/Classes/StatsDTO';
import { Userfullinfo } from 'src/app/Services/Classes/UserFullInfo';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
isTableReview:boolean;
isTableUsers:boolean;
isStats:boolean;
isSuccesful:boolean;
isFail:boolean;
isTableInfoShop:boolean;
isTableKategory:boolean;
shop:Shop[]
users:Userfullinfo[];
kategory:Kategory;
review:Review;
stats:StatsDTO
basicData: any;
charts:ChartDTO
  basicOptions: any;
  constructor(private router:ActivatedRoute,private adminservice:AdminService) { }

  ngOnInit(): void {
    this.adminservice.ChartStats().subscribe(data => {
      const labels = data.map(item=>  item[1]);
      const values = data.map(item => item[0]);
      const documentStyle = getComputedStyle(document.documentElement);
  const textColor = documentStyle.getPropertyValue('--text-color');
  const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
  const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

  this.basicData = {
      labels: labels,
      datasets: [
          {
              label: 'Stats write review for shop',
              data: values,
              backgroundColor: ['rgba(255, 159, 64, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)'],
              borderColor: ['rgb(255, 159, 64)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)'],
              borderWidth: 1
          }
      ]
  };

  this.basicOptions = {
      plugins: {
          legend: {
              labels: {
                  color: textColor
              }
          }
      },
      scales: {
          y: {
              beginAtZero: true,
              ticks: {
                  color: textColorSecondary
              },
              grid: {
                  color: surfaceBorder,
                  drawBorder: false
              }
          },
          x: {
              ticks: {
                  color: textColorSecondary
              },
              grid: {
                  color: surfaceBorder,
                  drawBorder: false
              }
          }
      }
  };
    })
    this.router.params.subscribe(params => {
      this.isTableReview = false;
      this.isTableUsers = false;
      this.isTableInfoShop = false;
      this.isTableKategory = false;
      this.isStats = false;
  
      switch (params['name']) {
        case 'review':
          this.isTableReview = true;
          this.GetAllReview();
          break;
        case 'users':
          this.isTableUsers = true;
          this.GetAllUsers();
          break;
        case 'infoshop':
          this.isTableInfoShop = true;
          this.GetAllShop();
          break;
        case 'kategory':
          this.isTableKategory = true;
          this.GetAllKategory();
          break;
          default :
          this.isStats = true;
          this.GetStats();
          break;
      }
    });
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

  GetStats(){
    this.adminservice.StatsCount().subscribe(data=>{
      this.stats=data;
    })
  }


}
