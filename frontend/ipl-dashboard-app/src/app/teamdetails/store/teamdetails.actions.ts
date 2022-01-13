import {Action} from "@ngrx/store";
import {Match} from "../../shared/models/Match";

export const FETCH_MATCHES = "[matches] get matches";
export const SET_MATCHES = "[matches] set matches";

export class FetchMatches implements Action {
  readonly type = FETCH_MATCHES;
  constructor(public payload: { team: string; year: number }) {}
}

export class SetMatches implements Action {
  readonly type = SET_MATCHES;
  constructor(public payload: Match[]) {
  }
}

export type MatchActions = FetchMatches |
  SetMatches;
