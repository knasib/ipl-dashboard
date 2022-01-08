import {Component, OnDestroy, OnInit} from '@angular/core';
import {Team} from "../shared/models/Team";
import {Subscription} from "rxjs";
import { Store } from '@ngrx/store';

import * as fromApp from '../store/app.reducer';
import * as teamsActions from './store/teams.actions'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
  teams: Team[];
  subscription: Subscription;

  constructor(private store: Store<fromApp.AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(new teamsActions.FetchTeams());
    this.subscription = this.store.select("teams").subscribe((teamsState) => {
      this.teams = teamsState.teams;
    });

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}


