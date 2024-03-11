import { Directive, HostListener } from "@angular/core";
import { AngularFireAuth } from "@angular/fire/compat/auth";
import { GoogleAuthProvider } from "@firebase/auth";
import {Router} from "@angular/router";

@Directive({
  selector: '[appGoogleSso]'
})
export class GoogleSsoDirective {

  constructor(private angularFireAuth: AngularFireAuth, private router: Router) { }
  @HostListener("click")
  async onClick() {
    const creds = await this.angularFireAuth.signInWithPopup(
      new GoogleAuthProvider(),
    );
   if(creds.user) {
        this.router.navigate(['/profile-page']);
    }
  }
}
