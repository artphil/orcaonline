import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';

import { GtinComponent } from './gtin.component';
import { SharedModule } from './../../shared/shared.module';
import { DialogService } from 'primeng/dynamicdialog';

describe('GtinComponent', () => {
  let component: GtinComponent;
  let fixture: ComponentFixture<GtinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GtinComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule,
        ButtonModule,
        InputTextModule,
        DropdownModule,
        ToastModule,
        SharedModule
      ],
      providers: [MessageService, DialogService,
        {
          provide: ActivatedRoute,
          useValue: {
            paramMap: of({ get: (key) => 'value' }),
            snapshot: {
              parent: {
                params: {
                  id: 1
                }
              },
              paramMap: {
                get(name: string): string {
                  return '';
                }
              }
            }
          }
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GtinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
