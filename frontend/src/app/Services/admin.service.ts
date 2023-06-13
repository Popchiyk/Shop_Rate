import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Kategory } from './Classes/Kategory';
import { Review } from './Classes/Review';
import { Shop } from './Classes/Shop';
import { ShopRequest } from './Classes/ShopRequest';
import { Userfullinfo } from './Classes/UserFullInfo';
import { StatsDTO } from './Classes/StatsDTO';
import { ChartDTO } from './Classes/ChartDTO';
import { StatisticResult } from './Classes/StatisticsResult';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  ipv4="localhost:8080/api/v1";
  constructor(private http:HttpClient) { }

 
  public GetAllUser():Observable<Userfullinfo[]>{
    return this.http.get<Userfullinfo[]>("http://"+this.ipv4+"/admin/users")
  }

  public GetAllShop():Observable<Shop[]>{
    return this.http.get<Shop[]>("http://"+this.ipv4+"/admin/shops")
  }

  public GetAllKategory():Observable<Kategory>{
    return this.http.get<Kategory>("http://"+this.ipv4+"/admin/kategorys")
  }

  public GetAllReview():Observable<Review>{
    return this.http.get<Review>("http://"+this.ipv4+"/admin/reviews")
  }

  public AddShop(shop:ShopRequest):Observable<any>{
    return this.http.post("http://"+this.ipv4+"/admin/add/shop",shop)
  } 

  public onLoadImageShop(updateImg :FormData,id:number):Observable<any>{
    return this.http.post('http://'+this.ipv4+'/shop/addimg/'+id,updateImg);
  }

  public AddKategory(kategory:string):Observable<any>{
    return this.http.post('http://'+this.ipv4+'/admin/add/kategory',kategory)
  }

  public FindShop(id:number):Observable<Shop>{
    return this.http.get<Shop>('http://'+this.ipv4+'/shop/find/'+id)
  }

  public UpdateShop(shop:ShopRequest,id:number):Observable<any>{
    return this.http.put<any>('http://'+this.ipv4+'/admin/update/shop/'+id,shop)
  }

  public  FindKategory(id:number):Observable<Kategory>{
    return this.http.get<Kategory>('http://'+this.ipv4+'/admin/find/kategory/'+id)
  }

  public UpdateKategory(id:number,kategory:string):Observable<any>{
    return this.http.put<any>('http://'+this.ipv4+'/admin/update/kategory/'+id,kategory)
  }

  public DeleteShop(id:number):Observable<void>{
    return this.http.delete<void>('http://'+this.ipv4+'/admin/delete/shop/'+id)
  }
  
  public DeleteKategory(id:number):Observable<void>{
    return this.http.delete<void>('http://'+this.ipv4+'/admin/delete/kategory/'+id)
  }

  public DeleteUser(id:number):Observable<void>{
    return this.http.delete<void>('http://'+this.ipv4+'/admin/delete/user/'+id)
  }

  public DeleteReview(id:number):Observable<void>{
    return this.http.delete<void>('http://'+this.ipv4+'/admin/delete/review/'+id)
  }

  public StatsCount():Observable<StatsDTO>{
    return this.http.get<StatsDTO>('http://'+this.ipv4+'/admin/stats')
  }

  public ChartStats():Observable<any>{
    return this.http.get<any>('http://'+this.ipv4+'/admin/chart')
  }

  public Derivastion():Observable<StatisticResult>{
    return this.http.get<StatisticResult>('http://'+this.ipv4+'/admin/reviews/derevastion')
  }
}
