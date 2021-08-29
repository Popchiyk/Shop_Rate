import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/Services/admin.service';
import { Kategory } from 'src/app/Services/Classes/Kategory';
import { Shop } from 'src/app/Services/Classes/Shop';
import { ShopRequest } from 'src/app/Services/Classes/ShopRequest';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
isAddPhotoToShop:boolean;
isAddInfoShop:boolean;
isAddkategory:boolean;
isSuccesful:boolean;
formKategory:FormGroup;
formInfoShop:FormGroup;
formAddPhoto:FormGroup;
kategory:Kategory;
selectedfile:File;
shop:ShopRequest;
shops:Shop[];
  constructor(private router:ActivatedRoute, private formbuilder:FormBuilder,private adminService:AdminService,private _router:Router) { 


    this.formAddPhoto=this.formbuilder.group({
      name_shop:['',[Validators.required]]
    })
    this.formKategory=this.formbuilder.group({
      name_kategory:['',[Validators.required,Validators.maxLength(30)]]
    });
    this.formInfoShop=this.formbuilder.group({
      name:['',[Validators.required,Validators.minLength(1),Validators.maxLength(15)]],
      phone:['+380',[Validators.required,Validators.maxLength(13)]],
      email:['',[Validators.required,Validators.email]],
      website:['',[Validators.required,Validators.maxLength(20)]],
      contact_people:['',[Validators.required,Validators.maxLength(20)]],
      kategory:['',[Validators.required]]
    })
    this.shop={
      contact_people:'',
      email:'',
      kategory:0,
      name:'',
      phone:'',
      website:''
    }
  }

  ngOnInit(): void {
    this.router.params.subscribe(param=>{
      if(param['name']=='infoshop'){
        this.isAddInfoShop=true;
        this.isAddkategory=false;
        this.GetAllKategory()
      }

      if(param['name']=='kategory'){
        this.isAddInfoShop=false;
        this.isAddkategory=true;
      }
      if(param['name'] == 'photoToShop'){
        this.isAddPhotoToShop=true;
        this.isAddkategory=false;
        this.isAddInfoShop=false;
        this.GetAllShops();
      }
    })
  }

  onFileSelected(event){
    this.selectedfile = event.target.files[0];
  }

  GetAllKategory(){
    this.adminService.GetAllKategory().subscribe(data=>{
      this.kategory=data;
    })
  }

  GetAllShops(){
    this.adminService.GetAllShop().subscribe(data=>{
      this.shops=data;
      console.log(this.shops)
    })
  }

  AddToKategory(){
     this.adminService.AddKategory(this.formKategory.get('name_kategory').value).subscribe(data=>{
      this.isSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this._router.navigate(['admin']);
       }, 3000);
     })
  }

  AddToShop(){
    this.shop.contact_people = this.formInfoShop.get('contact_people').value;
    this.shop.email = this.formInfoShop.get('email').value;
    this.shop.kategory = this.formInfoShop.get('kategory').value;
    this.shop.name = this.formInfoShop.get('name').value;
    this.shop.phone = this.formInfoShop.get('phone').value;
    this.shop.website = this.formInfoShop.get('website').value;
    this.adminService.AddShop(this.shop).subscribe(data=>{
      this.isSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this._router.navigate(['admin']);
       }, 3000);
    })
  }
  AddPhotoToShop(){
    let id =parseInt(this.formAddPhoto.get('name_shop').value);
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile',this.selectedfile,this.selectedfile.name)
    this.adminService.onLoadImageShop(uploadImageData,id).subscribe(data=>{
      this.isSuccesful=true;
      window.scrollTo(0,0);
      setTimeout(() => {
        this._router.navigate(['admin']);
       }, 3000);
    })
  }
}
