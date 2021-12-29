package com.ipl.dashboard.model.Representation.assembler;

import com.ipl.dashboard.constant.Constants;
import com.ipl.dashboard.controller.MatchController;
import com.ipl.dashboard.controller.TeamController;
import com.ipl.dashboard.model.Representation.TeamRepresentationModel;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.model.converter.ModelRepresentationConverter;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeamRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<Team, TeamRepresentationModel> {

    public TeamRepresentationModelAssembler() {
        super(TeamController.class, TeamRepresentationModel.class);
    }

    @Override
    @NonNull
    public TeamRepresentationModel toModel(@NonNull Team team) {
        TeamRepresentationModel teamRepresentationModel = ModelRepresentationConverter.convertToTeamRepresentationModel(team);
        teamRepresentationModel.add(
                linkTo(methodOn(TeamController.class).getTeams()).withRel(Constants.TEAMS)
        );
        teamRepresentationModel.add(
                linkTo(MatchController.class).slash(team.getName()).slash(Constants.YEARS).slash(Constants.CURRENT_YEAR).withRel(Constants.MATCHES)
        );
        return teamRepresentationModel;
    }

    public List<TeamRepresentationModel> toModels(List<Team> teams) {
        return teams.stream().map(this::toModel).collect(Collectors.toList());
    }
}
