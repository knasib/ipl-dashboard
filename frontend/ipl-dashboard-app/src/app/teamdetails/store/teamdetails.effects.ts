import {Injectable} from "@angular/core";
import {Actions, Effect, ofType} from "@ngrx/effects";
import {map, switchMap} from "rxjs";
import {HttpClient, HttpResponse} from "@angular/common/http";
import {environment} from "../../../environments/environment";

import {Match} from "../../shared/models/Match";
import * as MatchAction from "./teamdetails.actions";

@Injectable()
export class TeamdetailsEffects {

  @Effect()
  matches = this.actions$.pipe(
    ofType(MatchAction.FETCH_MATCHES),
    switchMap((data: MatchAction.FetchMatches) => {
      return this.http.get<HttpResponse<any>>(
        `${environment.baseUrl}/teams/` + data.payload.team + "/years/" + data.payload.year
      )
        .pipe(
          map((response: HttpResponse<any>) => {
            let matches = [];
            let match: Match;
            response["matchRepresentationModels"].map(element => {
              delete element.links
              match = {
                ...element
              };
              matches.push(match);
            })
            return new MatchAction.SetMatches(matches);
          })
        );
    })
  );

  constructor(private actions$: Actions,
              private http: HttpClient) {}
}

