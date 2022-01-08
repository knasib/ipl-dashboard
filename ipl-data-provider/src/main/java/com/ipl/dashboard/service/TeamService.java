package com.ipl.dashboard.service;

import com.ipl.dashboard.model.Representation.MatchRepresentationModels;
import com.ipl.dashboard.model.Representation.TeamRepresentationModels;

public interface TeamService {
    TeamRepresentationModels getTeams();
    MatchRepresentationModels getMatches(String team, int year);
}
