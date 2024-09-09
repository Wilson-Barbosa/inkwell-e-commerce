import { Injectable } from '@angular/core';
import { ThemeType } from '../ThemeType';
import { ThemeDetals } from '../ThemeDetails';

@Injectable({
    providedIn: 'root'
})
export class ThemeServiceService {

    // Map Containing the colors for each theme
    private themes: Map<ThemeType, ThemeDetals> = new Map([
        [ThemeType.DARK, {
            primary: "#005CBB",
            secondary: "#D7E3FF",
            text: "#ffffff",
            background: "#000000",
        }],
        [ThemeType.LIGHT, {
            primary: "#005CBB",
            secondary: "#D7E3FF",
            text: "#000000",
            background: "#ffffff",
        }],
        [ThemeType.LIGHT, {
            primary: "#005CBB",
            secondary: "#D7E3FF",
            text: "#000000",
            background: "#ffffff",
        }]
    ]);

    constructor() { }

    // Receives a Theme as parameters, saves it on the localStorage and then calls the method to change its colors
    setTheme(theme: ThemeType): void {
        localStorage.setItem('theme', theme);
        this.applyColors(theme);
    }

    // Receives a type for a parameter, then changes the variables colors on .root based upon it
    applyColors(theme: ThemeType): void {
        document.documentElement.style.setProperty("--text-color", this.themes.get(theme)!.text);
        document.documentElement.style.setProperty("--background", this.themes.get(theme)!.background);
        document.documentElement.style.setProperty("--primary", this.themes.get(theme)!.primary);
        document.documentElement.style.setProperty("--secondary", this.themes.get(theme)!.secondary);
    }

    // Looks in the local storage for a theme, if does not find it then returns the default as light
    getCurrentTheme(): ThemeType {
        const currentTheme: string | null = localStorage.getItem('theme');
        return currentTheme ? currentTheme as ThemeType : ThemeType.LIGHT;
    }


}
