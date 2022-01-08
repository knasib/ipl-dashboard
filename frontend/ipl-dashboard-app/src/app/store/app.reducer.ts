import {ActionReducerMap} from "@ngrx/store";

import * as teamsReducer from '../home/store/teams.reducer';

export interface AppState {
  teams: teamsReducer.State
}

export const appReducer: ActionReducerMap<AppState> = {
  teams: teamsReducer.teamsReducer,
}
