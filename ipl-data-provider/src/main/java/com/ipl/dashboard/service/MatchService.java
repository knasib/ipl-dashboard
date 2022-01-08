package com.ipl.dashboard.service;

import com.ipl.dashboard.model.Representation.MatchRepresentationModel;

public interface MatchService {
    MatchRepresentationModel getMatch(String matchId);
}
