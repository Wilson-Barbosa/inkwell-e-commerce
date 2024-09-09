import { ThemeServiceService } from './themes/services/theme-service.service';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./core/navbar/navbar.component";
import { FooterComponent } from "./core/footer/footer.component";
import { ThemeType } from './themes/ThemeType';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet, NavbarComponent, FooterComponent, NgIf],
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

    title = 'inkwell';

    constructor(private themeService: ThemeServiceService) {}


    ngOnInit(): void {
        // Sets the initial theme upon component initialization
        this.themeService.setTheme(this.themeService.getCurrentTheme());
    }


    setDarkMode(): void {
        this.themeService.setTheme(ThemeType.DARK);
    }

    setLightMode(): void {
        this.themeService.setTheme(ThemeType.LIGHT);
    }

    getActiveTheme(): string {
        return this.themeService.getCurrentTheme();
    }

}

