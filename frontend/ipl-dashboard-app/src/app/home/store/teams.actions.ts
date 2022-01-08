import {Action} from "@ngrx/store";
import {Team} from "../../shared/models/Team";

export const FETCH_TEAMS = "[teams] fetch teams"
export const SET_TEAMS = "[teams] set teams"

export class FetchTeams implements Action {
  readonly type = FETCH_TEAMS;
}

export class SetTeams implements Action {
  readonly type = SET_TEAMS;
  constructor(public payload: Team[]) {
  }
}


export type TeamsAction = FetchTeams
  | SetTeams;
