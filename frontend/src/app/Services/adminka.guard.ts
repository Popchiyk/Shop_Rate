import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminkaGuard implements CanActivate {
  constructor(private loginin:AuthService,private _router:Router ){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let isLogin = this.loginin.isLogin('token');
      let role = this.loginin.isLogin('role');
      if(isLogin && role ==="ROLE_ADMIN"){
        return true;
      }else{
        this._router.navigate(['**'])
      }
    return true;
  }
  
}
