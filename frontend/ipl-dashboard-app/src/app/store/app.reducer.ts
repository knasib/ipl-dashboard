import {ActionReducerMap} from "@ngrx/store";

import * as teamsReducer from '../home/store/teams.reducer';
import * as matchesReducer from '../teamdetails/store/teamdetails.reducer'

export interface AppState {
  teams: teamsReducer.State,
  matches: matchesReducer.State
}

export const appReducer: ActionReducerMap<AppState> = {
  teams: teamsReducer.teamsReducer,
  matches: matchesReducer.teamdetailsReducer
}
