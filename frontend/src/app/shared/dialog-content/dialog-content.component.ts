import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-content',
  standalone: true,
  imports: [MatDialogModule],
  templateUrl: './dialog-content.component.html',
  styleUrl: './dialog-content.component.scss'
})
export class DialogContentComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string }) { }

}
