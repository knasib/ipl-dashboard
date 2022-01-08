import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamtileComponent } from './teamtile.component';

describe('TeamtileComponent', () => {
  let component: TeamtileComponent;
  let fixture: ComponentFixture<TeamtileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamtileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamtileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
