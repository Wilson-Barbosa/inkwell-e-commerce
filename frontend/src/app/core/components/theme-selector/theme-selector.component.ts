import { Component, OnInit } from '@angular/core';
import { ThemeServiceService } from '../../../themes/services/theme.service';
import { ThemeType } from '../../../themes/ThemeType';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-theme-selector',
    standalone: true,
    imports: [NgIf],
    templateUrl: './theme-selector.component.html',
    styleUrl: './theme-selector.component.scss'
})
export class ThemeSelectorComponent implements OnInit {

    constructor(private themeService: ThemeServiceService) { }

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
