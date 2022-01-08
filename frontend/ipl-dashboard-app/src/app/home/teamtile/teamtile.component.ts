import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Team} from "../../shared/models/Team";

@Component({
  selector: 'app-teamtile',
  templateUrl: './teamtile.component.html',
  styleUrls: ['./teamtile.component.scss']
})
export class TeamtileComponent implements OnInit, AfterViewInit {
  @Input() team: Team;


  constructor() { }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
  }

}
