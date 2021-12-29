package com.ipl.dashboard.service;

import com.ipl.dashboard.model.Representation.TeamRepresentationModel;
import com.ipl.dashboard.model.Representation.TeamRepresentationModels;
import com.ipl.dashboard.model.Team;

import java.util.List;

public interface TeamService {
    TeamRepresentationModels getTeams();
}
