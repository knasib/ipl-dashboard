import {Match} from "../../shared/models/Match";
import * as actions from "./teamdetails.actions";

export interface State {
  matches: Match[];
}

const initialState: State = {
  matches: []
};

export function teamdetailsReducer(state = initialState, action: actions.MatchActions) {
  switch (action.type) {
    case actions.FETCH_MATCHES :
      return {
        ...state
      }

    case actions.SET_MATCHES:
      return {
        ...state,
        matches: action.payload
      }
  }
}
