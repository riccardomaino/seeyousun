import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatListModule } from '@angular/material/list';
import { MatRadioModule } from '@angular/material/radio';

import { ExploreComponent } from './explore/explore.component';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { FilterDialogComponent } from './filter-dialog/filter-dialog.component';
import { MatChipsModule } from '@angular/material/chips';
import { ResortPageComponent } from './resort-page/resort-page.component';
import { BookDialogComponent } from './book-dialog/book-dialog.component';
import { BookDialogSelectComponent } from './book-dialog-select/book-dialog-select.component';
import {MatSelectModule} from '@angular/material/select';
import { LoginPageComponent } from './login-page/login-page.component';


// Firebase
import { initializeApp } from "firebase/app";
import { AngularFireModule } from "@angular/fire/compat";
import { AngularFireAuthModule } from "@angular/fire/compat/auth";
import { environment } from "../environments/environment";
import { GoogleSsoDirective } from './google-sso.directive';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { ProfilePageComponent } from './profile-page/profile-page.component';
import { ResumePageComponent } from './resume-page/resume-page.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { bearerTokenInterceptor } from './bearer-token.interceptor';
import { FooterComponent } from './footer/footer.component';
import { ReviewDialogComponent } from './review-dialog/review-dialog.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ExploreComponent,
    FilterDialogComponent,
    ResortPageComponent,
    BookDialogComponent,
    BookDialogSelectComponent,
    LoginPageComponent,
    GoogleSsoDirective,
    SignupPageComponent,
    ProfilePageComponent,
    ResumePageComponent,
    FooterComponent,
    ReviewDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    MatSlideToggleModule,
    MatInputModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    HttpClientModule,
    MatDialogModule,
    MatButtonModule,
    MatButtonToggleModule,
    FormsModule,
    MatChipsModule,
    MatSelectModule,
    MatListModule,
    MatRadioModule,
    AngularFireAuthModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
  ],
  providers: [provideHttpClient(withInterceptors([bearerTokenInterceptor]))],
  bootstrap: [AppComponent]
})

export class AppModule {
  
 }
