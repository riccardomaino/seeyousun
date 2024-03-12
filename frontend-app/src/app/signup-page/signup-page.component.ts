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
  mailIsInvalid: boolean = false;
  userName: string = '';
  email: string = '';
  password1: string = '';
  password2: string = '';


  toLoginPage() {
    this.router.navigate(['/login-page']);
  }

  checkIfMailValid() {
    // Controlla se l'email è vuota
    if (this.email === '') {
      this.mailIsInvalid = true; // Imposta mailIsInvalid a true se l'email è vuota
    } else {
      // Verifica se l'email è valida utilizzando un'espressione regolare per il formato dell'email
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (emailRegex.test(this.email)) {
        this.mailIsInvalid = false; // L'email è valida, quindi mailIsInvalid è false
      } else {
        this.mailIsInvalid = true; // L'email non è valida, quindi mailIsInvalid è true
      }
    }
  }


  async signUp() {
    try {
      if (this.password1 !== this.password2 || this.password1 === '' || this.email === '' || this.userName === '') {
        this.pIsInvalid = true;
        return;
      }
  
      // Creazione dell'utente con email e password
      if(!this.pIsInvalid && !this.mailIsInvalid) {
        const userCredential = await this.auth.createUserWithEmailAndPassword(this.email, this.password2);


        // Impostazione del displayName per l'utente appena creato
        await userCredential.user?.updateProfile({
          displayName: this.userName
        });

        console.log('Utente creato con successo:', userCredential.user);
      }
      // Reindirizzamento alla pagina del profilo
      this.router.navigate(['/profile-page']);
    } catch (error) {
      console.error('Errore durante la creazione dell\'utente:', error);
    }
  }
  
  
}
