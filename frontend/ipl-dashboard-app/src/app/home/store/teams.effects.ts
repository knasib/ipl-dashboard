import {Injectable} from "@angular/core";
import { Actions, Effect, ofType } from '@ngrx/effects';
import {HttpClient, HttpResponse} from "@angular/common/http";

import * as TeamAction from './teams.actions'
import {switchMap, map} from "rxjs";
import {environment} from "../../../environments/environment";
import {Team} from "../../shared/models/Team";

@Injectable()
export class TeamsEffects {

  @Effect()
  teams = this.actions$.pipe(
    ofType(TeamAction.FETCH_TEAMS),
    switchMap((data: TeamAction.FetchTeams) => {
      return this.http.get<HttpResponse<any>>(
        `${environment.baseUrl}/teams`
      )
        .pipe(
          map((response: HttpResponse<any>) => {
            let teams = [];
            response["teamRepresentationModels"].map(element => {
              teams.push(new Team(element.name, element.totalWins, element.totalMatches));
            })
            return new TeamAction.SetTeams(teams);
          })
        );
    })
  );

  constructor(private actions$: Actions,
              private http: HttpClient) {}
}
