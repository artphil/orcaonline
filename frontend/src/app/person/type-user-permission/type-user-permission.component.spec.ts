import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeUserPermissionComponent } from './type-user-permission.component';

describe('TypeUserPermissionComponent', () => {
  let component: TypeUserPermissionComponent;
  let fixture: ComponentFixture<TypeUserPermissionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TypeUserPermissionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeUserPermissionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
