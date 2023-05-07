import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { JwtToken } from './Classes/JwtProvider';
import { LoginRequest } from './Classes/LoginRequest';
import { RegistrashionRequest } from './Classes/RegistrashionRequest';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  ipv4="localhost:8081/api/v1";
  constructor(private http:HttpClient) { }
  public registrashion(registrashionUser:RegistrashionRequest):Observable<any>{
    return this.http.post('http://'+this.ipv4+'/auth/signup',registrashionUser);
  }
  public login(loginRequest:LoginRequest):Observable<boolean>{
    return this.http.post<JwtToken>('http://'+this.ipv4+'/auth/login',loginRequest).pipe(map(data=>{
      document.cookie="token="+data.jwt;
      document.cookie="username="+data.username;
      document.cookie="role="+data.roles;
      document.cookie="auth="+true;
      return true;
    }));
  }
  isLogin(name:string){
    var cookie = " " + document.cookie;
	var search = " " + name + "=";
	var setStr = null;
	var offset = 0;
	var end = 0;
	if (cookie.length > 0) {
		offset = cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = cookie.indexOf(";", offset)
			if (end == -1) {
				end = cookie.length;
			}
			setStr = unescape(cookie.substring(offset, end));
		}
	}
	return(setStr);
  }
  Logout(){
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
  }
}
