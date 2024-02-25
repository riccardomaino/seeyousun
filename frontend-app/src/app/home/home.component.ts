import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [MatDatepickerModule, MatNativeDateModule, MatFormFieldModule, MatInputModule]
})
export class HomeComponent {

  constructor(private router: Router) {}

  navigateToExplore() {
    this.router.navigate(['/explore']);
  }
}
