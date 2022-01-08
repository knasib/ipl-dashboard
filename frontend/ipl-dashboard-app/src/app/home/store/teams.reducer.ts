import {Team} from "../../shared/models/Team";
import * as actions from './teams.actions';


export interface State {
  teams: Team[];
}

const initialState: State = {
  teams: []
};

export function teamsReducer(state = initialState, action: actions.TeamsAction) {
  switch (action.type) {
    case actions.FETCH_TEAMS:
      return {
        ... state
      }
    case actions.SET_TEAMS:
      return {
        ... state,
        teams: action.payload
      }
    default:
      return state;
  }
}
