import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Team} from "../../shared/models/Team";
import {ActivatedRoute, Router} from "@angular/router";

import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-teamtile',
  templateUrl: './teamtile.component.html',
  styleUrls: ['./teamtile.component.scss']
})
export class TeamtileComponent implements OnInit, AfterViewInit {
  @Input() team: Team;


  constructor(private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
  }

  navigateTeamPage() {
    console.log("Navigate To Team Page " + this.team.name)
    let url = "/teams/" + this.team.name + `/${environment.endYear}`;
    this.router.navigate([url], { relativeTo: this.route })
  }
}
