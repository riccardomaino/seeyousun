import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.scss']
})
export class ConfirmationDialogComponent {
    constructor( @Inject(MAT_DIALOG_DATA) public data: { title: string, message: string, status: boolean }) { }

    ngOnInit() {
        console.log(this.data);
    }

}
