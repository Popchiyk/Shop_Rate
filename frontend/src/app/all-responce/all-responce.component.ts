import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../Services/Classes/Review';
import { Shop } from '../Services/Classes/Shop';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-all-responce',
  templateUrl: './all-responce.component.html',
  styleUrls: ['./all-responce.component.css']
})
export class AllResponceComponent implements OnInit {
shop:Shop;
review:Review[];
arr:Array<any>;
id:number;
  constructor(private router:Router,private userService:UserService,private _router:ActivatedRoute) { }

  ngOnInit() {
    this._router.params.subscribe(params=>{
      if(params['']==='rating'){
        let id =parseInt(localStorage.getItem("id"));
        this.userService.FindShop(id).subscribe(data=>{
          this.shop=data;
            this.shop.logo ='data:image/jpeg;base64,'+this.shop.logo;
            this.userService.FindReviewBySHop(id).subscribe(data1=>{
              this.review=data1;
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
              this.review.sort(function(c,b){
                if(c.ball<b.ball){
                  return -1;
                }
                if(c.ball>b.ball){
                  return 1;
                }
                return 0;
                
              })
              this.review.reverse();
              let a = Array<Review[]>();
              for(var i=0;i<this.review.length;i+=2){
                a.push(this.review.slice(i,i+2))
              }
              this.arr = a;
              for(var j=0;j< this.review.length;j++){
                if(this.review[j].logo){
                  this.review[j].logo='data:image/jpeg;base64,'+ this.review[j].logo;
                  console.log(this.review[j].logo)
                }
                if(!this.review[j].logo){
                  this.review[j].logo="";
                  
                }
              }
            })
          
        })
      }
      else{
        this.userService.FindShop(params['']).subscribe(data=>{
          this.shop=data;
            this.id=parseInt(params['']);
            this.shop.logo ='data:image/jpeg;base64,'+this.shop.logo;
            this.userService.FindReviewBySHop(this.id).subscribe(data1=>{
              this.review=data1;
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
                if(this.review[j].logo){
                  this.review[j].logo='data:image/jpeg;base64,'+ this.review[j].logo;
                  console.log(this.review[j].logo)
                }
                if(!this.review[j].logo){
                  this.review[j].logo="";
                  
                }
              }
            })
          
        })
      }
  
  });
}

  HrefToResponce(){
    this.router.navigate(['writeresponce'+'/'+this.id])
  }
}
