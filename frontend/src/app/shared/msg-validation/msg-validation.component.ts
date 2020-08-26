import { Component, OnInit, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-msg-validation',
  template: `
  <div *ngIf="hasError()" class="msg-error">
    {{ text }}
  </div>
  `,
  styles: [`
  .msg-error {
    color: red;
    margin-top: 10px
  }
`]
})
export class MsgValidationComponent implements OnInit {

  @Input() error: string;
  @Input() control: FormControl;
  @Input() text: string;

  constructor() { }

  ngOnInit(): void {
  }

  hasError(): boolean {
    return this.control.hasError(this.error) && this.control.dirty;
  }
}
