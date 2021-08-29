import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrashionComponent } from './registrashion/registrashion.component';
import { HeaderComponent } from './header/header.component';
import { MainComponent } from './main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { Routes,RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { ExceptionComponent } from './exception/exception.component';
import { UpdateAccountSettingComponent } from './update-account-setting/update-account-setting.component';
import { UpdateAccountLoginAndPasswordComponent } from './update-account-login-and-password/update-account-login-and-password.component';
import { FooterComponent } from './footer/footer.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { KatalogComponent } from './katalog/katalog.component';
import { ShopStatsComponent } from './shop-stats/shop-stats.component';
import { AllResponceComponent } from './all-responce/all-responce.component';
import { WriteresponceComponent } from './writeresponce/writeresponce.component';
import { AuthGuard } from './Services/auth.guard';
import { AdminComponent } from './adminka/admin/admin.component';
import { HeadingforadmComponent } from './adminka/headingforadm/headingforadm.component';
import { FooterforadminComponent } from './adminka/footerforadmin/footerforadmin.component';
import { AdminkaGuard } from './Services/adminka.guard';
import { AddComponent } from './adminka/add/add.component';
import { UpdateComponent } from './adminka/update/update.component';

const routes:Routes=[
  {
    path:'',redirectTo:'main',pathMatch:'full'
  },
  {
    path:'main',component:MainComponent
  },
  {
    path:'main/:name',component:MainComponent
  },
  {
    path:'login',component:LoginComponent
  },
  {
    path:'registrashion',component:RegistrashionComponent
  },
  {
    path:'profile',component:ProfileComponent,canActivate:[AuthGuard]
  },
  {
    path:'accountSetting',component:UpdateAccountSettingComponent,canActivate:[AuthGuard]
  },
  {
    path:'accountLoginAndPassword',component:UpdateAccountLoginAndPasswordComponent,canActivate:[AuthGuard]
  },
  {
    path:'favourite',component:FavouriteComponent,canActivate:[AuthGuard]
  },
  {
    path:'katalog',component:KatalogComponent
  },
  {
    path:'katalog/:',component:KatalogComponent
  },
  {
    path:'infoshop/:',component:ShopStatsComponent
  },
  {
    path:'allresponce/:',component:AllResponceComponent
  },
  {
    path:'writeresponce/:',component:WriteresponceComponent
  },
  {
    path:'writeresponce/:',component:WriteresponceComponent
  },
  {
    path:'admin',component:AdminComponent,canActivate:[AdminkaGuard]
  },
  {
    path:'admin/:table/:name',component:AdminComponent,canActivate:[AdminkaGuard]
  },
  {
    path:'add/:name',component:AddComponent,canActivate:[AdminkaGuard]
  },
  {
    path:'update/:name/:id',component:UpdateComponent,canActivate:[AdminkaGuard]
  },
  {
    path:'**',component:ExceptionComponent
  },
  
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrashionComponent,
    HeaderComponent,
    MainComponent,
    ProfileComponent,
    ExceptionComponent,
    UpdateAccountSettingComponent,
    UpdateAccountLoginAndPasswordComponent,
    FooterComponent,
    FavouriteComponent,
    KatalogComponent,
    ShopStatsComponent,
    AllResponceComponent,
    WriteresponceComponent,
    AdminComponent,
    HeadingforadmComponent,
    FooterforadminComponent,
    AddComponent,
    UpdateComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }