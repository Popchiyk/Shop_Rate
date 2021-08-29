import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/auth.service';

@Component({
  selector: 'app-headingforadm',
  templateUrl: './headingforadm.component.html',
  styleUrls: ['./headingforadm.component.css']
})
export class HeadingforadmComponent implements OnInit {
username:string;
  constructor(private authService:AuthService,private router:Router) { }

  ngOnInit(): void {
    this.username=this.authService.isLogin('username')
  }

  LogOut(){
    this.router.navigate(['login'])
    this.authService.Logout();
      
  }
}
