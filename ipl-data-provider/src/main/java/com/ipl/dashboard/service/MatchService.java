package com.ipl.dashboard.service;

import com.ipl.dashboard.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchService {
    List<Match> getMatches(String team, int year);
    Optional<Match> getMatch(String matchId);
}
