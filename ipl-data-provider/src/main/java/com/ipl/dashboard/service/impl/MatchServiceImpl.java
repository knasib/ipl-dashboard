package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.converter.ModelConverter;
import com.ipl.dashboard.exception.ResourceNotFoundException;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.model.Representation.MatchRepresentationModels;
import com.ipl.dashboard.model.Representation.assembler.MatchRepresentationModelAssembler;
import com.ipl.dashboard.repository.LosingMatchByTeamAndYearRepository;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.repository.WinningMatchByTeamAndYearRepository;
import com.ipl.dashboard.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository;
    private final LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository;
    private final MatchRepresentationModelAssembler matchRepresentationModelAssembler;

    public MatchServiceImpl(MatchRepository matchRepository, WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository, LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository, MatchRepresentationModelAssembler matchRepresentationModelAssembler) {
        this.matchRepository = matchRepository;
        this.winningMatchByTeamAndYearRepository = winningMatchByTeamAndYearRepository;
        this.losingMatchByTeamAndYearRepository = losingMatchByTeamAndYearRepository;
        this.matchRepresentationModelAssembler = matchRepresentationModelAssembler;
    }


    @Override
    public MatchRepresentationModels getMatches(String team, int year) {
        List<Match> matches = new ArrayList<>();

        matches.addAll(winningMatchByTeamAndYearRepository.findAllByTeam1AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());
        matches.addAll(losingMatchByTeamAndYearRepository.findAllByTeam2AndYear(team, year)
                .stream().map(ModelConverter::convertToMatch).toList());

        return MatchRepresentationModels.builder()
                .matchRepresentationModels(matchRepresentationModelAssembler.toCollectionModel(matches))
                .build();
    }

    @Override
    public MatchRepresentationModel getMatch(String matchId) {
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if(matchOptional.isPresent())
            return matchRepresentationModelAssembler.toModel(matchOptional.get());

        throw new ResourceNotFoundException("Match with Id " + matchId + " not found.");
    }
}