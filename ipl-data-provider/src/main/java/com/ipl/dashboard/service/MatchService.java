package com.ipl.dashboard.service;

import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.model.Representation.MatchRepresentationModels;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    MatchRepresentationModels getMatches(String team, int year);
    MatchRepresentationModel getMatch(String matchId);
}
