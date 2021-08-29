import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../Services/auth.service';
import { Review } from '../Services/Classes/Review';
import { Shop } from '../Services/Classes/Shop';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-shop-stats',
  templateUrl: './shop-stats.component.html',
  styleUrls: ['./shop-stats.component.css']
})
export class ShopStatsComponent implements OnInit {

  constructor(private userService:UserService,private router:ActivatedRoute,private authservice:AuthService) { }
  shop:Shop
  review:Review[];
  arr:Array<any>;
  ngOnInit(): void {
    let token = this.authservice.isLogin('token');
    this.router.params.subscribe(params=>{
      if(token){
        this.userService.FindAllInfoForUser(params['']).subscribe(data=>{
          this.shop=data;
          localStorage.setItem("id",params[''])
          this.shop.logo='data:image/jpeg;base64,'+this.shop.logo;
        })
        this.userService.FindReviewBySHop(params['']).subscribe(data=>{
          this.review=data;
          for(var c=0;c<this.review.length;c++){
            let date = this.review[c].data.split("");
            let mount=date[5]+date[6];
            switch(mount){
              case "01":this.review[c].data=+date[8]+date[9]+" Січня "+date[0]+date[1]+date[2]+date[3];break;
              case "02":this.review[c].data=+date[8]+date[9]+" Лютого "+date[0]+date[1]+date[2]+date[3];break;
              case "03":this.review[c].data=+date[8]+date[9]+" Березня "+date[0]+date[1]+date[2]+date[3];break;
              case "04":this.review[c].data=+date[8]+date[9]+" Квітня "+date[0]+date[1]+date[2]+date[3];break;
              case "05":this.review[c].data=+date[8]+date[9]+" Травня "+date[0]+date[1]+date[2]+date[3];break;
              case "06":this.review[c].data=+date[8]+date[9]+" Червня "+date[0]+date[1]+date[2]+date[3];break;
              case "07":this.review[c].data=+date[8]+date[9]+" Липня "+date[0]+date[1]+date[2]+date[3];break;
              case "08":this.review[c].data=+date[8]+date[9]+" Серпня "+date[0]+date[1]+date[2]+date[3];break;
              case "09":this.review[c].data=+date[8]+date[9]+" Вересня "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Жовтня "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Листопада "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Грудня "+date[0]+date[1]+date[2]+date[3];break;
          }
          }
          
         for(var j=0;j<this.review.length;j++){
          if(this.review[j].logo){
            this.review[j].logo='data:image/jpeg;base64,'+ this.review[j].logo;
            console.log(this.review[j].logo)
          }
          if(!this.review[j].logo){
            this.review[j].logo="";
            
          }
         }
          let a = Array<Review[]>();
          for(var i=0;i<this.review.length;i+=2){
            a.push(this.review.slice(i,i+2))
          }
          this.arr = a;
          
        })

      }else{
        this.userService.FindShop(params['']).subscribe(data=>{
          this.shop=data;
          this.shop.logo='data:image/jpeg;base64,'+this.shop.logo;
        })
        this.userService.FindReviewBySHop(params['']).subscribe(data=>{
          this.review=data;
          for(var c=0;c<this.review.length;c++){
            let date = this.review[c].data.split("");
            let mount=date[5]+date[6];
            switch(mount){
              case "01":this.review[c].data=+date[8]+date[9]+" Січень "+date[0]+date[1]+date[2]+date[3];break;
              case "02":this.review[c].data=+date[8]+date[9]+" Лютий "+date[0]+date[1]+date[2]+date[3];break;
              case "03":this.review[c].data=+date[8]+date[9]+" Березень "+date[0]+date[1]+date[2]+date[3];break;
              case "04":this.review[c].data=+date[8]+date[9]+" Квітень "+date[0]+date[1]+date[2]+date[3];break;
              case "05":this.review[c].data=+date[8]+date[9]+" Травень "+date[0]+date[1]+date[2]+date[3];break;
              case "06":this.review[c].data=+date[8]+date[9]+" Червень "+date[0]+date[1]+date[2]+date[3];break;
              case "07":this.review[c].data=+date[8]+date[9]+" Липень "+date[0]+date[1]+date[2]+date[3];break;
              case "08":this.review[c].data=+date[8]+date[9]+" Серпень "+date[0]+date[1]+date[2]+date[3];break;
              case "09":this.review[c].data=+date[8]+date[9]+" Вересень "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Жовтень "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Листопад "+date[0]+date[1]+date[2]+date[3];break;
              case "10":this.review[c].data=+date[8]+date[9]+" Грудень "+date[0]+date[1]+date[2]+date[3];break;
          }
          }
          let a = Array<Review[]>();
          for(var i=0;i<this.review.length;i+=2){
            a.push(this.review.slice(i,i+2))
          }
          this.arr = a;
          for(var j=0;j< this.review.length;j++){
            this.review[j].logo='data:image/jpeg;base64,'+ this.review[j].logo;
          }
        })
      }
     
    })
  }

}
