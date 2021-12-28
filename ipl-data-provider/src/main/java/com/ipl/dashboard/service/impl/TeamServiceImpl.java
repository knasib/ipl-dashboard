package com.ipl.dashboard.service.impl;

import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.TeamRepository;
import com.ipl.dashboard.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
