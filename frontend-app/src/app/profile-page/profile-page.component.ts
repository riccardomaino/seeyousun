import { Component } from '@angular/core';
import { AngularFireAuth } from "@angular/fire/compat/auth";
import { Router } from '@angular/router';
import {BeachService} from "../services/beach.service";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent {
  constructor(public auth: AngularFireAuth, public router: Router, public service: BeachService) {}
  isUserLogged: boolean = false;
  userName: string = '';
  userMail: string = '';

  ngOnInit() {
    this.auth.authState.subscribe(user => {
      this.isUserLogged = !!user;
      this.userName = user?.displayName ?? '';
      this.userMail = user?.email ?? '';
      this.loadUserData();
    });
  }

  loadUserData() {
    if(this.isUserLogged) {
      this.service.getReservationForUser().subscribe((reservation) => {
        console.log(reservation.data);
      });
    }
  }

    logOut() {
      this.auth.signOut();
      this.router.navigate(['']);
    }
}
