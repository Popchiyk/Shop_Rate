
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-exception',
  templateUrl: './exception.component.html',
  styleUrls: ['./exception.component.css']
})
export class ExceptionComponent implements OnInit {

  constructor(private _router:Router) { }
  goToMain(){
   this._router.navigate(['main'])
  }
  ngOnInit(): void {
  }

 
}
