import { Component } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { FormControl, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.scss'],
})
export class SignupPageComponent {
  constructor(public router: Router, public auth: AngularFireAuth) {}
  hide: boolean = true;
  pIsInvalid: boolean = false;
  userName: string = '';
  email: string = '';
  password1: string = '';
  password2: string = '';

  toLoginPage() {
    this.router.navigate(['/login-page']);
  }
    

  async signUp() {
    try {
      if (this.password1 !== this.password2 || this.password1 === '' || this.email === '' || this.userName === '') {
        console.log("Le password non corrispondono o l'email è vuota.");
        this.pIsInvalid = true;
        return;
      }
  
      // Creazione dell'utente con email e password
      const userCredential = await this.auth.createUserWithEmailAndPassword(this.email, this.password2);
      
      // Impostazione del displayName per l'utente appena creato
      await userCredential.user?.updateProfile({ 
        displayName: this.userName
      });
  
      console.log('Utente creato con successo:', userCredential.user);
  
      // Reindirizzamento alla pagina del profilo
      this.router.navigate(['/profile-page']);
    } catch (error) {
      console.error('Errore durante la creazione dell\'utente:', error);
    }
  }
  
  
}
