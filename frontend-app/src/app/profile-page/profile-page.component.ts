import { Component } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/compat/auth";
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent {
  constructor(public auth: AngularFireAuth,public router: Router) {}
  isUserLogged: boolean = false;
  userName: string = '';
  userMail: string = '';

  ngOnInit() {
    this.auth.authState.subscribe(user => {
      this.isUserLogged = !!user;
      this.userName = user?.displayName ?? '';
      this.userMail = user?.email ?? '';
      console.log(user);
    });
  }

    logOut() {
      this.auth.signOut();
      this.router.navigate(['']);
    }
}
