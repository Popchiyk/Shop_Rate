import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/Services/admin.service';
import { Kategory } from 'src/app/Services/Classes/Kategory';
import { Shop } from 'src/app/Services/Classes/Shop';
import { ShopRequest } from 'src/app/Services/Classes/ShopRequest';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
isShops:boolean;
isKategory:boolean;
formInfoShop:FormGroup;
formKategory:FormGroup
shop:Shop;
isSuccesful:boolean;
shops:ShopRequest;
idShop:number;
kategory:Kategory;
findkategory:Kategory;
  constructor(private _router:ActivatedRoute,private adminService:AdminService,private formbuilder:FormBuilder,private router:Router) {
    this.formInfoShop=this.formbuilder.group({
      name:['',[Validators.required,Validators.minLength(2),Validators.maxLength(15)]],
      phone:['+380',[Validators.required,Validators.maxLength(13)]],
      email:['',[Validators.required,Validators.email]],
      website:['',[Validators.required,Validators.maxLength(20)]],
      contact_people:['',[Validators.required,Validators.maxLength(20)]],
      kategory:['',[Validators.required]]
    })
    this.formKategory=this.formbuilder.group({
      name_kategory:['',[Validators.required,Validators.maxLength(30)]]
    });
    this.shops={
      contact_people:'',
      email:'',
      kategory:0,
      name:'',
      phone:'',
      website:''
    }
   }

   ngOnInit(): void {
    this._router.params.subscribe(params => {
      const name = params['name'];
  
      if (name === 'shops') {
        this.isShops = true;
        this.idShop = parseInt(params['id']);
        this.GetFindInfoShop(this.idShop);
        this.GetAllKategory();
      } else if (name === 'kategory') {
        this.isKategory = true;
        console.log(true);
        const id = parseInt(params['id']);
        this.GetFindKategory(id);
      }
    });
  }

  GetAllKategory(){
    this.adminService.GetAllKategory().subscribe(data=>{
      this.kategory=data;
    })
  }

  GetFindInfoShop(id:number){
    this.adminService.FindShop(id).subscribe(data=>{
      this.shop=data;
    })
  }

  GetFindKategory(id:number){
    this.adminService.FindKategory(id).subscribe(data=>{
      this.findkategory=data;
    })
  }

  UpdateKategory(){
    this.adminService.UpdateKategory(this.findkategory.id_kategory,this.formKategory.get('name_kategory').value).subscribe(data=>{
      this.isSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this.router.navigate(['admin']);
       }, 3000);
     })
  }

  UpdateShop(){
    this.shops.contact_people = this.formInfoShop.get('contact_people').value;
    this.shops.email = this.formInfoShop.get('email').value;
    this.shops.kategory = this.formInfoShop.get('kategory').value;
    this.shops.name = this.formInfoShop.get('name').value;
    this.shops.phone = this.formInfoShop.get('phone').value;
    this.shops.website = this.formInfoShop.get('website').value;
    this.adminService.UpdateShop(this.shops,this.shop.id).subscribe(data=>{
      this.isSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this.router.navigate(['admin']);
       }, 3000);
    })
  }
}
