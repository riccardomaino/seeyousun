import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-resume-page',
  templateUrl: './resume-page.component.html',
  styleUrls: ['./resume-page.component.scss']
})
export class ResumePageComponent implements AfterViewInit {
  @ViewChild('navbar', {static: false}) navbar: ElementRef;
  navbarHeight: number = 0;
  data: any;

  constructor(private route: ActivatedRoute) {
    // Initialize the 'navbar' property
    this.navbar = new ElementRef(null);
  }

  ngOnInit(): void {
    // Recupera i dati dallo stato di navigazione
    this.data = this.route.snapshot.paramMap.get('data');
    console.log(this.data); // Fai qualcosa con i dati ricevuti
  }

  ngAfterViewInit(): void {
    // Verifica se navbar è stato inizializzato correttamente
    if (this.navbar && this.navbar.nativeElement) {
      // Calcola l'altezza della navbar dopo che è stata inizializzata
      this.navbarHeight = this.navbar.nativeElement.offsetHeight;
      console.log('Navbar height: ' + this.navbarHeight);
    } else {
      console.error('Navbar non inizializzato correttamente.');
    }
  }
}
