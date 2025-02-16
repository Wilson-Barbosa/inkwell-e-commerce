import { Component } from '@angular/core';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expired-session-dialog',
  standalone: true,
  imports: [MatDialogModule],
  templateUrl: './expired-session-dialog.component.html',
  styleUrl: './expired-session-dialog.component.scss'
})
export class ExpiredSessionDialogComponent {

  constructor(private router: Router, private dialogService: MatDialog) { }

  navigateToLogin(): void {
    this.dialogService.closeAll();
    this.router.navigateByUrl("/login");
  }
}
