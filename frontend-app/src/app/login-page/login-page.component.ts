import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent {

  constructor(public router: Router, public auth: AngularFireAuth) {}
  email: string = '';
  password: string = '';
  hide: boolean = true;
  pIsInvalid: boolean = false;

  //Sing in with mail and password
  login() {
    this.auth.signInWithEmailAndPassword(this.email, this.password).then(() => {
      this.router.navigate(['/profile-page']);
    }).catch(error => {
      console.log(error);
      this.pIsInvalid = true;
    });
  }

  openSignUpPage() {
    this.router.navigate(['/signup-page']);
  }
}
