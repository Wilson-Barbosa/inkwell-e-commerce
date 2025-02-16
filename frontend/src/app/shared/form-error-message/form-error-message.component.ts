import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-form-error-message',
    standalone: true,
    imports: [],
    templateUrl: './form-error-message.component.html',
    styleUrl: './form-error-message.component.scss'
})
export class FormErrorMessageComponent {

    /** Error message that should be displayed */
    @Input({ required: true }) message: string = '';

}
