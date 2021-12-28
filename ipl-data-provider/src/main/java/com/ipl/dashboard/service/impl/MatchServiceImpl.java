package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.converter.ModelConverter;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.repository.LosingMatchByTeamAndYearRepository;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.repository.WinningMatchByTeamAndYearRepository;
import com.ipl.dashboard.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository;
    private final LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository;

    public MatchServiceImpl(MatchRepository matchRepository,
                            WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository,
                            LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository) {
        this.matchRepository = matchRepository;
        this.winningMatchByTeamAndYearRepository = winningMatchByTeamAndYearRepository;
        this.losingMatchByTeamAndYearRepository = losingMatchByTeamAndYearRepository;
    }

    @Override
    public List<Match> getMatches(String team, int year) {
        List<Match> matches = new ArrayList<>();

        matches.addAll(winningMatchByTeamAndYearRepository.findAllByTeam1AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());
        matches.addAll(losingMatchByTeamAndYearRepository.findAllByTeam2AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());

        return matches;
    }

    @Override
    public Optional<Match> getMatch(String matchId) {
        return matchRepository.findById(matchId);
    }
}
