import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/compat/auth';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  constructor(public router: Router, public auth: AngularFireAuth) {}
  isUserLoggedIn: boolean = false;
  user: string = '';

  ngOnInit() {
    this.auth.authState.subscribe(user => {
      this.isUserLoggedIn = !!user;
      this.user = user?.displayName ?? '';
    });
  }

  openLoginPage() {
    if(!this.isUserLoggedIn)
      this.router.navigate(['/login-page']);  
  }

  openProfilePage() {
    if(this.isUserLoggedIn)
      this.router.navigate(['/profile-page']);
  }

  logOut() {
    this.auth.signOut();
    this.router.navigate(['']);
  }

  

}
