import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ExploreComponent } from './explore/explore.component';
import { ResortPageComponent } from './resort-page/resort-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { ResumePageComponent } from './resume-page/resume-page.component';
import { authGuard } from './auth.guard';

const routes: Routes = [
  { path: '', component: HomeComponent }, // Questa è la route per la homepage
  { path: 'explore', component: ExploreComponent }, // Questa è la route per la home 1.1
  { path: 'resort-page', component: ResortPageComponent }, // Questa è la route per la resortPage
  { path: 'login-page', component: LoginPageComponent },
  { path: 'signup-page', component: SignupPageComponent },
  { path: 'profile-page', component: ProfilePageComponent, canActivate: [authGuard]},
  { path: 'resume-page', component: ResumePageComponent },
 
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
