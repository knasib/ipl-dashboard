import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Store} from "@ngrx/store";

import * as fromApp from '../store/app.reducer';
import * as matchActions from './store/teamdetails.actions'
import {Subscription} from "rxjs";
import {Match} from "../shared/models/Match";

import {environment} from "../../environments/environment";
import {FetchMatches} from "./store/teamdetails.actions";

@Component({
  selector: 'app-teamdetails',
  templateUrl: './teamdetails.component.html',
  styleUrls: ['./teamdetails.component.scss']
})
export class TeamdetailsComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  teamName: string;
  year: number = environment.endYear;
  matches: Match[];
  startYear: number = environment.startYear;
  endYear: number = environment.endYear;
  years: number[] = [];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<fromApp.AppState>) { }

  ngOnInit(): void {

    this.fillYears();

    this.route.params.subscribe((params: Params) => {
      this.teamName = params['teamname'];
      this.year = +params['year'];
    });

   this.store.dispatch(new matchActions.FetchMatches({team: this.teamName, year: this.year}));

    this.subscription = this.store.select("matches").subscribe((matchState) => {
      this.matches = matchState.matches;
    });

  }

  private fillYears() {
    for (let i = this.endYear; i >= this.startYear; i--) {
      this.years.push(i);
    }
  }

  getMatches(selectedYear) {
    this.year = selectedYear;
    this.store.dispatch(new FetchMatches({year: selectedYear, team: this.teamName}))
    let url = "/teams/" + this.teamName + "/" + this.year;
    this.router.navigate([url]).then(_ => {})
  }

  navigateHome() {
    this.router.navigate(["/home"]).then(_ => {});
  }

  navigateTeamPage(match: Match) {
    let team = match.team1 == this.teamName ? match.team2 : match.team1;
    let url = "/teams/" + team + "/" + this.year;
    this.router.navigate([url]).then(_ => {})
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}
