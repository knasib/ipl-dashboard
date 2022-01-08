package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.exception.ResourceNotFoundException;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.model.Representation.assembler.MatchRepresentationModelAssembler;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final MatchRepresentationModelAssembler matchRepresentationModelAssembler;

    public MatchServiceImpl(MatchRepository matchRepository, MatchRepresentationModelAssembler matchRepresentationModelAssembler) {
        this.matchRepository = matchRepository;
        this.matchRepresentationModelAssembler = matchRepresentationModelAssembler;
    }

    @Override
    public MatchRepresentationModel getMatch(String matchId) {
        log.info("Get match with match id {}", matchId);
        Optional<Match> matchOptional = matchRepository.findById(matchId);
        if(matchOptional.isPresent())
            return matchRepresentationModelAssembler.toModel(matchOptional.get());

        log.error("Match with Id {} not found.", matchId);
        throw new ResourceNotFoundException("Match with Id " + matchId + " not found.");
    }
}
