package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.model.Representation.TeamRepresentationModels;
import com.ipl.dashboard.model.Representation.assembler.TeamRepresentationModelAssembler;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.TeamRepository;
import com.ipl.dashboard.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepresentationModelAssembler teamRepresentationModelAssembler;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepresentationModelAssembler teamRepresentationModelAssembler, TeamRepository teamRepository) {
        this.teamRepresentationModelAssembler = teamRepresentationModelAssembler;
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamRepresentationModels getTeams() {
        log.info("Get All Team details");
        List<Team> teams = teamRepository.findAll();
        return TeamRepresentationModels.builder()
                .teamRepresentationModels(teamRepresentationModelAssembler.toModels(teams))
                .build();
    }
}
