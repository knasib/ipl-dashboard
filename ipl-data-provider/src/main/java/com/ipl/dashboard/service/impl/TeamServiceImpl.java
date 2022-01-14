package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.converter.ModelConverter;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModels;
import com.ipl.dashboard.model.Representation.TeamRepresentationModels;
import com.ipl.dashboard.model.Representation.assembler.MatchRepresentationModelAssembler;
import com.ipl.dashboard.model.Representation.assembler.TeamRepresentationModelAssembler;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.LosingMatchByTeamAndYearRepository;
import com.ipl.dashboard.repository.TeamRepository;
import com.ipl.dashboard.repository.WinningMatchByTeamAndYearRepository;
import com.ipl.dashboard.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepresentationModelAssembler teamRepresentationModelAssembler;
    private final TeamRepository teamRepository;

    private final WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository;
    private final LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository;
    private final MatchRepresentationModelAssembler matchRepresentationModelAssembler;

    public TeamServiceImpl(TeamRepresentationModelAssembler teamRepresentationModelAssembler,
                           TeamRepository teamRepository,
                           WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository,
                           LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository,
                           MatchRepresentationModelAssembler matchRepresentationModelAssembler) {

        this.teamRepresentationModelAssembler = teamRepresentationModelAssembler;
        this.teamRepository = teamRepository;
        this.winningMatchByTeamAndYearRepository = winningMatchByTeamAndYearRepository;
        this.losingMatchByTeamAndYearRepository = losingMatchByTeamAndYearRepository;
        this.matchRepresentationModelAssembler = matchRepresentationModelAssembler;
    }

    @Override
    public TeamRepresentationModels getTeams() {
        log.info("Get All Team details");
        List<Team> teams = teamRepository.findAll();
        return TeamRepresentationModels.builder()
                .teamRepresentationModels(teamRepresentationModelAssembler.toModels(teams))
                .build();
    }

    @Override
    public MatchRepresentationModels getMatches(String team, int year) {
        log.info("Get all matches for {} in year {}", team, year);
        List<Match> matches = new ArrayList<>();

        matches.addAll(winningMatchByTeamAndYearRepository.findAllByTeam1AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());
        matches.addAll(losingMatchByTeamAndYearRepository.findAllByTeam2AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());

        matches.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));

        return MatchRepresentationModels.builder()
                .matchRepresentationModels(matchRepresentationModelAssembler.toModels(matches))
                .build();
    }
}
