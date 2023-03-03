import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UpdateAccountLoginAndPasswordComponent } from '../update-account-login-and-password/update-account-login-and-password.component';
import { AuthService } from './auth.service';
import { Kategory } from './Classes/Kategory';
import { Review } from './Classes/Review';
import { ReviewRequest } from './Classes/ReviewRequest';
import { Shop } from './Classes/Shop';
import { UpdateLoginAndPassword } from './Classes/UpdateLoginAndPassword';
import { UserDataInfo } from './Classes/UserInfo';
import { UserInfoRequest } from './Classes/UserRequestInfo';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  ipv4="localhost:8081";
  constructor(private http:HttpClient,private authService:AuthService) { }
  
  public UserInfo():Observable<UserDataInfo>{
   return this.http.get<UserDataInfo>('http://'+this.ipv4+'/user/getInfoUser/'+this.authService.isLogin('username'))
  }

  public UpdateLoginAndPassword(oldLoginAndPass:UpdateLoginAndPassword):Observable<any>{
    return this.http.put<any>("http://"+this.ipv4+"/auth/update/login/password/"+this.authService.isLogin('username'),oldLoginAndPass)
  }

  public onLoadImage(updateImg :FormData):Observable<any>{
      return this.http.post('http://'+this.ipv4+'/user/setImg/'+this.authService.isLogin('username'),updateImg);
  }

  public deleteImg():Observable<any>{
    return this.http.post('http://'+this.ipv4+'/user/deleteImg/'+this.authService.isLogin('username'),null)
  }

  public UpdateData(userequest:UserInfoRequest):Observable<any>{
    return this.http.post('http://'+this.ipv4+'/user/setInfo/'+this.authService.isLogin('username'),userequest);
  }

  public GetAllShop():Observable<Shop[]>{
    return this.http.get<Shop[]>('http://'+this.ipv4+'/shop/allShop');
  }

  public OnLoadImageShop(updateImg:FormData,id:number):Observable<any>{
    return this.http.post('http://'+this.ipv4+'/shop/addimg/'+id,updateImg);
  }

  public GetAllKategory():Observable<Kategory[]>{
    return this.http.get<Kategory[]>('http://'+this.ipv4+'/shop/kategory');
  }

  public FindShop(id:number):Observable<Shop>{
    return this.http.get<Shop>('http://'+this.ipv4+'/shop/find/'+id)
  }

  public FindReviewBySHop(id:number):Observable<Review[]>{
    return this.http.get<Review[]>('http://'+this.ipv4+'/user/review/allpost/'+id)
  }

  public Favourite(username:string):Observable<Shop[]>{
    return this.http.get<Shop[]>('http://'+this.ipv4+'/user/getfavourite/'+username);
  }

  public findByKategory(id:number):Observable<Shop[]>{
    return this.http.get<Shop[]>('http://'+this.ipv4+'/shop/kategory/find/'+id)
  }
  
  public sendReview(revierequest:ReviewRequest,id:number):Observable<any>{
    return this.http.post("http://"+this.ipv4+"/user/add/review/"+id+"/"+this.authService.isLogin('username'),revierequest);
  }

  public GetAllForUser():Observable<Shop[]>{
    return this.http.get<Shop[]>('http://'+this.ipv4+'/user/getall/shop/'+this.authService.isLogin('username'));
  }

  public SetFavourite(id:number):Observable<any>{
    return this.http.post("http://"+this.ipv4+"/shop/favourite/"+this.authService.isLogin('username')+"/"+id,null)
  }

  public DeleteFavourite(id:number):Observable<any>{
    return this.http.delete<any >('http://'+this.ipv4+'/user/delete/favourite/'+id)
  }

  public FindAllInfoForUser(id:number):Observable<Shop>{
    return this.http.get<Shop>("http://"+this.ipv4+"/user/find/user/"+this.authService.isLogin('username')+"/"+id)
  }

  public FindKategorybyUser(id:number):Observable<Shop[]>{
    return this.http.get<Shop[]>("http://"+this.ipv4+"/user/find/kategory/"+this.authService.isLogin('username')+"/"+id)
  }
}
