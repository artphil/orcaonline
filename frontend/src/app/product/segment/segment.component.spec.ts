import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';

import { SegmentComponent } from './segment.component';
import { SharedModule } from './../../shared/shared.module';

describe('SegmentComponent', () => {
  let component: SegmentComponent;
  let fixture: ComponentFixture<SegmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SegmentComponent],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        FormsModule,
        DynamicDialogModule,
        ButtonModule,
        InputTextModule,
        DropdownModule,
        ToastModule,
        SharedModule
      ],
      providers: [MessageService,
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
    fixture = TestBed.createComponent(SegmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
