import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { HomeComponent} from "../home/home.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  constructor(public router: Router, public auth: AngularFireAuth, private home: HomeComponent) {}
  isUserLoggedIn: boolean = false;
  user: string = '';
  resortName: string = '';

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

  resortByName(resortName: string) {
    console.log('Ciao' + resortName);
    if (resortName.trim() !== '') {
      if (this.router.url === '') {
        // Chiama la funzione del controller della home
        this.home.resortByName(resortName);
      } else {
        // Routing a home e chiama la funzione del controller della home
        this.router.navigate(['']);
        this.home.resortByName(resortName);
      }
    }
  }


}
